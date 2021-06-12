package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.OtherFile;
import com.quan.datn.model.database.BenhNhan;
import com.quan.datn.model.database.NhanVien;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import com.quan.datn.model.response.BacSiResponse;
import com.quan.datn.repository.NhanVienRepository;
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
public class NhanVienManager {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private FileManager fileManager;

    public Object getAllNhanVien() throws ExceptionResponse {
        List<NhanVien> dsNhanViens = nhanVienRepository.findAll();
        if (dsNhanViens.size() <= 0){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK,dsNhanViens);
    }

    public Object getAllBacSi() throws ExceptionResponse {
        List<NhanVien> dsBacSi = nhanVienRepository.getAllBacSi();
        if (dsBacSi.size() <= 0){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        List<BacSiResponse> responses = dsBacSi.stream().map((bacSi) -> {
            BacSiResponse response = new BacSiResponse();
            response.setMaBS(bacSi.getMaNV());
            response.setTenBS(bacSi.getHo() + " "+ bacSi.getTen());
            response.setTrinhDo(bacSi.getTrinhDo());
            response.setChucVu(bacSi.getChucVu());
            return response;
        }).collect(Collectors.toList());
        return new CommonResponse(HttpStatus.OK,responses);

    }

    public Object deleteNhanVien(String maNV) throws ExceptionResponse{
        NhanVien nv = nhanVienRepository.findByMaNV(maNV);
        if (nv == null){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS );
        }
        try{
            nhanVienRepository.delete(nv);
            return new CommonResponse(HttpStatus.OK,SUCCESS);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST, AN_UNKNOWN_ERROR);
        }
    }

    public Object updateNhanVien(NhanVien updateRequest, MultipartFile file) throws ExceptionResponse {
        NhanVien nhanVien = nhanVienRepository.findByMaNV(updateRequest.getMaNV());
        if (nhanVien == null){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN,VALUES_NOT_EXITS);
        }
        if (file != null) {
            OtherFile fileResponse = fileManager.upload(file);
            System.out.println("=========== File Path: " + fileResponse.getFilePath());
            updateRequest.setAvatar(fileResponse.getFilePath());
        }
        try {
            updateRequest.setPassword(nhanVien.getPassword());
            NhanVien response = nhanVienRepository.save(updateRequest);
            return new CommonResponse(HttpStatus.OK,response);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
        }
    }

    public Object addNhanVien(NhanVien request, MultipartFile file) throws ExceptionResponse {
        if (file != null) {
            OtherFile fileResponse = fileManager.upload(file);
            System.out.println("File Path: " + fileResponse.getFilePath());
            request.setAvatar(fileResponse.getFilePath());
        }
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            request.setPassword(encoder.encode("123456"));
            NhanVien response = nhanVienRepository.save(request);
            return new CommonResponse(HttpStatus.OK,response);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
        }
    }

    public Object login(LoginRequest request) throws ExceptionResponse {
        NhanVien nhanVien = nhanVienRepository.findByPhoneNumber(request.getPhoneNumber());
        if (nhanVien == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(request.getPassword(), nhanVien.getPassword())) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        nhanVien.setAccessToken(AuthenToken.generateToken(nhanVien));
        return new CommonResponse(HttpStatus.OK, nhanVien);
    }

    public Object updatePassword(UpdatePassRequest request) throws ExceptionResponse {
        NhanVien nhanVien = nhanVienRepository.findByPhoneNumber(request.getPhoneNumber());
        if (nhanVien == null) {
            throw new ExceptionResponse(HttpStatus.UNAUTHORIZED, USER_NOT_EXIST);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        nhanVien.setPassword(encoder.encode(request.getNewPassword()));
        nhanVienRepository.save(nhanVien);
        return new CommonResponse(HttpStatus.OK, SUCCESS);
    }
}
