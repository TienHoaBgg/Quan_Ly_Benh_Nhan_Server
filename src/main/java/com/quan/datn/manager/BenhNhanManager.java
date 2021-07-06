package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.common.MessageResponse;
import com.quan.datn.model.OtherFile;
import com.quan.datn.model.database.*;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.request.AddBenhNhanRequest;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import com.quan.datn.model.response.BenhAnResponse;
import com.quan.datn.model.response.BenhNhanResponse;
import com.quan.datn.model.response.PhongKhamResponse;
import com.quan.datn.repository.*;
import com.quan.datn.security.AuthenToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.quan.datn.common.MessageResponse.*;

@Controller
public class BenhNhanManager {

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    @Autowired
    private PhongBenhRepository phongBenhRepository;

    @Autowired
    private BenhAnRepository benhAnRepository;

    @Autowired
    private PhongKhamRepository phongKhamRepository;

    @Autowired
    private DonThuocRepository donThuocRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private BaoCaoRepository baoCaoRepository;

    @Autowired
    private FileManager fileManager;

    public Object addBenhNhan(AddBenhNhanRequest request, MultipartFile file) throws ExceptionResponse {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            BenhNhan benhNhan = request.getBenhNhan();
            benhNhan.setMatKhau(encoder.encode("123456"));
            if (file != null) {
                OtherFile fileResponse = fileManager.upload(file);
                System.out.println("======================== File Path: " + fileResponse.getFilePath());
                benhNhan.setAvatar(fileResponse.getFilePath());
            }
            BenhNhan response = benhNhanRepository.save(benhNhan);
            BenhAn benhAn = request.getBenhAn();
            benhAn.setMaBN(response.getMaBN());
            benhAnRepository.save(benhAn);
            BaoCao baoCao = new BaoCao(response);
            baoCao.setNoiDung("Bệnh nhân mới");
            baoCaoRepository.save(baoCao);
            return new CommonResponse(HttpStatus.OK, apply(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResponse(HttpStatus.BAD_REQUEST, UPDATE_VALUE_FALSE);
    }

    public Object deleteBenhNhan(String maBN) throws ExceptionResponse {
        BenhNhan bn = benhNhanRepository.findByMaBN(maBN);
        if (bn == null) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        List<BenhAn> dsBenhAn = benhAnRepository.findAllByMaBN(maBN);
        try {
            for (BenhAn benhAn : dsBenhAn) {
                benhAnRepository.delete(benhAn);
            }
            if (bn.getAvatar() != null && !bn.getAvatar().equals("")) {
                fileManager.deleteFile(bn.getAvatar());
            }
            benhNhanRepository.delete(bn);
            BaoCao baoCao = new BaoCao(bn);
            baoCao.setNoiDung("Bệnh nhân được xóa khỏi danh sách");
            baoCaoRepository.save(baoCao);
            return new CommonResponse(HttpStatus.OK, SUCCESS);
        } catch (Exception e) {
        }
        return new CommonResponse(HttpStatus.FORBIDDEN, AN_UNKNOWN_ERROR);
    }

    public Object updateBenhNhan(UpdateBenhNhanRequest request, MultipartFile file) throws ExceptionResponse {
        BenhNhan bn = benhNhanRepository.findByMaBN(request.getMaBN());
        if (bn == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BenhNhan benhNhan = request.getBenhNhan();
        bn.setCmnd(benhNhan.getCmnd());
        bn.setHo(benhNhan.getHo());
        bn.setTen(benhNhan.getTen());
        bn.setGioiTinh(benhNhan.getGioiTinh());
        bn.setNgaySinh(benhNhan.getNgaySinh());
        bn.setNgheNghiep(benhNhan.getNgheNghiep());
        bn.setDiaChi(benhNhan.getDiaChi());
        bn.setSdt(benhNhan.getSdt());
        bn.setTrangThai(benhNhan.getTrangThai());
        bn.setAmount(benhNhan.getAmount());
        bn.setPublicKey(benhNhan.getPublicKey());
        if (file != null) {
            OtherFile fileResponse = fileManager.upload(file);
            System.out.println("======================== File Path: " + fileResponse.getFilePath());
            bn.setAvatar(fileResponse.getFilePath());
        }
        try {
            benhNhanRepository.save(bn);
            BaoCao baoCao = new BaoCao(bn);
            baoCao.setNoiDung("Bệnh nhân được cập nhật thông tin");
            baoCaoRepository.save(baoCao);
            return new CommonResponse(HttpStatus.OK, apply(bn));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResponse(HttpStatus.FORBIDDEN, UPDATE_VALUE_FALSE);
    }

    public Object login(LoginRequest request) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(request.getPhoneNumber());
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getPassword(), benhNhan.getMatKhau())) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        benhNhan.setToken(AuthenToken.generateToken(benhNhan));
        return new CommonResponse(HttpStatus.OK, apply(benhNhan));
    }

    public Object updatePassword(UpdatePassRequest request) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(request.getPhoneNumber());
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getOldPassword(), benhNhan.getMatKhau())) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, OLD_PASSWORD_INCORRECT);
        }
        benhNhan.setMatKhau(encoder.encode(request.getNewPassword()));
        benhNhanRepository.save(benhNhan);
        return new CommonResponse(HttpStatus.OK, SUCCESS);
    }

    public Object resetPassword(UpdatePassRequest request) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(request.getPhoneNumber());
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        benhNhan.setMatKhau(encoder.encode(request.getNewPassword()));
        benhNhanRepository.save(benhNhan);
        return new CommonResponse(HttpStatus.OK, SUCCESS);
    }

    public Object getAllBenhNhanCho() throws ExceptionResponse {
        List<BenhNhan> listBenhNhan = benhNhanRepository.findAllByBenhNhanCho();
        if (listBenhNhan == null || listBenhNhan.size() <= 0) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, MessageResponse.VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK, listBenhNhan.stream().map(this::apply).collect(Collectors.toList()));
    }

    public Object getAllBenhNhanDieuTri() throws ExceptionResponse {
        List<BenhNhan> listBenhNhan = benhNhanRepository.findAllByBenhNhanDieuTri();
        if (listBenhNhan == null || listBenhNhan.size() <= 0) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, MessageResponse.VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK, listBenhNhan.stream().map(this::apply).collect(Collectors.toList()));
    }

    public Object getAllBenhNhan() throws ExceptionResponse {
        try {
            List<BenhNhan> listBenhNhan = benhNhanRepository.findAllBenhNhan();
            if (listBenhNhan == null || listBenhNhan.size() <= 0) {
                throw new ExceptionResponse(HttpStatus.FORBIDDEN, MessageResponse.VALUES_NOT_EXITS);
            }
            return new CommonResponse(HttpStatus.OK, listBenhNhan.stream().map(this::apply).collect(Collectors.toList()));
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, MessageResponse.VALUES_NOT_EXITS);
        }
    }

    private BenhNhanResponse apply(BenhNhan benhNhan) {
        BenhNhanResponse response = new BenhNhanResponse();
        response.setId(benhNhan.getId());
        response.setMaBN(benhNhan.getMaBN());
        response.setHoBn(benhNhan.getHo());
        response.setTenBn(benhNhan.getTen());
        response.setAvatar(benhNhan.getAvatar());
        response.setGioiTinh(benhNhan.getGioiTinh());
        response.setNgaySinh(benhNhan.getNgaySinh());
        response.setCmnd(benhNhan.getCmnd());
        response.setNgheNghiep(benhNhan.getNgheNghiep());
        response.setSdt(benhNhan.getSdt());
        response.setDiaChi(benhNhan.getDiaChi());
        response.setTrangThai(benhNhan.getTrangThai());
        response.setAmount(benhNhan.getAmount());
        response.setPublicKey(benhNhan.getPublicKey());
        response.setToken(benhNhan.getToken());
        BenhAn benhAn = benhAnRepository.findOneByMaBN(benhNhan.getMaBN());
        response.setBenhAnResponse(exportBenhAn(benhAn));
        List<DonThuoc> donThuocs = donThuocRepository.getDonThuocByMaBA(benhAn.getMaBA());
        int chiPhi = 0;
        if (donThuocs != null && donThuocs.size() > 0){
            for (DonThuoc donThuoc : donThuocs){
                chiPhi += donThuoc.getThanhTien();
            }
        }
        chiPhi += benhAn.getChiPhi();
        response.setVienPhi(chiPhi);
        return response;
    }

    private BenhAnResponse exportBenhAn(BenhAn benhAn) {
        BenhAnResponse response = new BenhAnResponse();
        response.setId(benhAn.getId());
        response.setMaBA(benhAn.getMaBA());
        response.setThoiGianKham(benhAn.getThoiGianKham());
        response.setCanNang(benhAn.getCanNang());
        response.setNhomMau(benhAn.getNhomMau());
        response.setNhietDo(benhAn.getNhietDo());
        response.setMach(benhAn.getMach());
        response.setHuyetAp(benhAn.getHuyetAp());
        response.setNhipTho(benhAn.getNhipTho());
        response.setLyDoKham(benhAn.getLyDoKham());
        response.setTinhTrangHT(benhAn.getTinhTrangHT());
        response.setBenhSu(benhAn.getBenhSu());
        response.setChanDoanSoBo(benhAn.getChanDoanSoBo());
        response.setYeuCauThem(benhAn.getYeuCauThem());
        response.setChanDoanSauCung(benhAn.getChanDoanSauCung());
        response.setHuongDieuTri(benhAn.getHuongDieuTri());
        response.setPhongBenh(phongBenhRepository.findByMaPB(benhAn.getMaPB()));
        PhongKham phongKham = phongKhamRepository.findByMaPK(benhAn.getMaPK());
        if (phongKham != null) {
            response.setPhongKham(exportPhongKham(phongKham));
        }
        return response;
    }

    private PhongKhamResponse exportPhongKham(PhongKham phongKham) {
        PhongKhamResponse response = new PhongKhamResponse();
        response.setId(phongKham.getId());
        response.setMaPK(phongKham.getMaPK());
        response.setTenPK(phongKham.getTenPK());
        response.setBacSi(nhanVienRepository.findByMaNV(phongKham.getMaBS()));
        return response;
    }

    public Object xuatVien(String maBN) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByMaBN(maBN);
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        try {
            benhNhan.setTrangThai("Đã xuất viện");
            BenhNhan response = benhNhanRepository.save(benhNhan);
            BaoCao baoCao = new BaoCao(response);
            baoCao.setNoiDung("Bệnh nhân được xuất viện");
            baoCaoRepository.save(baoCao);
            return new CommonResponse(HttpStatus.OK, response);
        } catch (Exception e) {
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST, AN_UNKNOWN_ERROR);
        }
    }

    public Object nhapVien(String maBN) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByMaBN(maBN);
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        try {
            benhNhan.setTrangThai("Đang điều trị");
            BenhNhan response = benhNhanRepository.save(benhNhan);
            BaoCao baoCao = new BaoCao(response);
            baoCao.setNoiDung("Bệnh nhân nhập viện");
            baoCaoRepository.save(baoCao);
            return new CommonResponse(HttpStatus.OK, response);
        } catch (Exception e) {
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST, AN_UNKNOWN_ERROR);
        }
    }

    public Object getInfoBenhNhan(String phoneNumber) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(phoneNumber);
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        return new CommonResponse(HttpStatus.OK, apply(benhNhan));
    }

    public Object getInfoByMaBn(String maBN) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByMaBN(maBN);
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        return new CommonResponse(HttpStatus.OK, apply(benhNhan));

    }

    public Object checkPhoneNumber(String phoneNumber) throws ExceptionResponse {
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(phoneNumber);
        if (benhNhan == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        return new CommonResponse(HttpStatus.OK, USER_EXIST);
    }
}
