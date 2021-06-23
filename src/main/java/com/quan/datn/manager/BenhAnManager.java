package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.DonThuocModel;
import com.quan.datn.model.database.*;
import com.quan.datn.model.response.BenhAnResponse;
import com.quan.datn.model.response.PhongKhamResponse;
import com.quan.datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static com.quan.datn.common.MessageResponse.*;

@Controller
public class BenhAnManager {

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    @Autowired
    private BenhAnRepository benhAnRepository;

    @Autowired
    private PhongBenhRepository phongBenhRepository;

    @Autowired
    private PhongKhamRepository phongKhamRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private DonThuocRepository donThuocRepository;

    public Object getAllBenhAn(String maBN) throws ExceptionResponse {
        List<BenhAn> benhAns = benhAnRepository.findAllByMaBN(maBN);
        if (benhAns == null || benhAns.size() <= 0) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK, benhAns.stream().map(this::exportBenhAn).collect(Collectors.toList()));
    }

    public Object getAllBenhAnByPhoneNumber(String phoneNumber) throws ExceptionResponse{
        BenhNhan benhNhan = benhNhanRepository.findByPhoneNumber(phoneNumber);
        List<BenhAn> benhAns = benhAnRepository.findAllByMaBN(benhNhan.getMaBN());
        if (benhAns == null || benhAns.size() <= 0) {
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK, benhAns.stream().map(this::exportBenhAn).collect(Collectors.toList()));
    }

    public Object addBenhAn(BenhAn request) throws ExceptionResponse {
        try {
            BenhNhan bn = benhNhanRepository.findByMaBN(request.getMaBN());
            bn.setTrangThai("Đang chờ khám");
            benhNhanRepository.save(bn);
            benhAnRepository.save(request);
            return new CommonResponse(HttpStatus.OK, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST, AN_UNKNOWN_ERROR);
        }
    }

    public Object updateBenhAn(BenhAn request) throws ExceptionResponse{
        try {
            BenhAn benhAn = benhAnRepository.findByMaBA(request.getMaBA());
            if (benhAn == null){
                throw  new ExceptionResponse(HttpStatus.FORBIDDEN,VALUES_NOT_EXITS);
            }
            benhAnRepository.save(request);
            return new CommonResponse(HttpStatus.OK, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST, AN_UNKNOWN_ERROR);
        }
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
        response.setChiPhi(benhAn.getChiPhi());
        response.setPhongBenh(phongBenhRepository.findByMaPB(benhAn.getMaPB()));
        PhongKham pk = phongKhamRepository.findByMaPK(benhAn.getMaPK());
        if (pk != null){
            response.setPhongKham(exportPhongKham(pk));
        }
        List<DonThuoc> dts = donThuocRepository.getDonThuocByMaBA(benhAn.getMaBA());
        if (dts != null && dts.size() > 0){
            response.setDonThuoc(dts);
        }
        return response;
    }

    private PhongKhamResponse exportPhongKham(PhongKham phongKham) {
        PhongKhamResponse response = new PhongKhamResponse();
        response.setId(phongKham.getId());
        response.setMaPK(phongKham.getMaPK());
        response.setTenPK(phongKham.getTenPK());
        NhanVien nv = nhanVienRepository.findByMaNV(phongKham.getMaBS());
        if (nv != null){
            response.setBacSi(nv);
        }
        return response;
    }

    public Object addDonThuoc(DonThuocModel request) throws ExceptionResponse{
        try{
            for(DonThuoc donThuoc : request.getDonthuocs()) {
                donThuocRepository.save(donThuoc);
            }
            return new CommonResponse(HttpStatus.OK, SUCCESS);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN,AN_UNKNOWN_ERROR);
        }
    }

}