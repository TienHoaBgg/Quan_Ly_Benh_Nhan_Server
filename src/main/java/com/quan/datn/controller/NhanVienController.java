package com.quan.datn.controller;


import com.google.gson.Gson;
import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.database.NhanVien;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import com.quan.datn.service.NhanVienManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.quan.datn.common.MessageResponse.INVALID_TOKEN;

@RestController
public class NhanVienController {

    @Autowired
    private NhanVienManagerService service;

    @PostMapping(value = Constants.URL_ADD_NHAN_VIEN)
    public Object addBenhNhan(
            @RequestParam(value = "avatar", required = false) MultipartFile file,
            @RequestParam(value = "profile") String request
    ) throws ExceptionResponse {
        NhanVien addRequest = new Gson().fromJson(request, NhanVien.class);
        return new ResponseEntity<>(service.addNhanVien(addRequest,file), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_UPDATE_NHAN_VIEN)
    public Object updateBenhNhan(
            @RequestParam(value = "avatar", required = false) MultipartFile file,
            @RequestParam(value = "profile") String request
    ) throws ExceptionResponse {
        NhanVien updateRequest = new Gson().fromJson(request, NhanVien.class);
        return new ResponseEntity<>(service.updateNhanVien(updateRequest,file), HttpStatus.OK);
    }

    @DeleteMapping(value = Constants.URL_DELETE_NHAN_VIEN)
    public Object deletePhongBenh(
            @RequestParam("manv") String maNV
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.deleteNhanVien(maNV),HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_NHAN_VIEN)
    public Object getAllNhanVien() throws ExceptionResponse {
        return new ResponseEntity<>(service.getAllNhanVien(), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BAC_SI)
    public Object getAllBacSi() throws ExceptionResponse{
        return new ResponseEntity<>(service.getAllBacSi(),HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_NHANVIEN_LOGIN)
    public Object login(
            @RequestBody @Valid LoginRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_NHANVIEN_UPDATE_PASS)
    public Object updatePassword(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody @Valid UpdatePassRequest request
    ) throws ExceptionResponse {
        if (token.isEmpty()) {
            return new ResponseEntity<>(new CommonResponse(HttpStatus.UNAUTHORIZED, INVALID_TOKEN), HttpStatus.UNAUTHORIZED);
        } else {
            token = token.substring(6);
            return new ResponseEntity<>(service.updatePassword(request), HttpStatus.OK);
        }
    }


}
