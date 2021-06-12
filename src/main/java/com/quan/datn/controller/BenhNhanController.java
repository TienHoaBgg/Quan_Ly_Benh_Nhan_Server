package com.quan.datn.controller;


import com.google.gson.Gson;
import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.request.AddBenhNhanRequest;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import com.quan.datn.service.BenhNhanManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.quan.datn.common.MessageResponse.INVALID_TOKEN;

@RestController
public class BenhNhanController {

    @Autowired
    private BenhNhanManagerService service;

    @GetMapping(value = Constants.URL_BENH_NHAN_XUAT_VIEN)
    public Object xuatVien(
            @RequestParam(value = "mabn") String MaBN
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.xuatVien(MaBN), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_BENH_NHAN_NHAP_VIEN)
    public Object nhapVien(
            @RequestParam(value = "mabn") String MaBN
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.nhapVien(MaBN), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_ADD_BENH_NHAN)
    public Object addBenhNhan(
            @RequestParam(value = "avatar", required = false) MultipartFile file,
            @RequestParam(value = "profile") String request
    ) throws ExceptionResponse{
        AddBenhNhanRequest AddBenhNhanRequest = new Gson().fromJson(request, AddBenhNhanRequest.class);
        return new ResponseEntity<>(service.addBenhNhan(AddBenhNhanRequest,file),HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_DELETE_BENHNHAN)
    public Object deleteBenhNhan(
            @RequestParam(value = "mabn") String MaBN
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.deleteBenhNhan(MaBN), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_UPDATE_BENHNHAN)
    public Object updateBenhNhan(
            @RequestParam(value = "avatar", required = false) MultipartFile file,
            @RequestParam(value = "profile") String request
    ) throws ExceptionResponse {
        UpdateBenhNhanRequest benhNhanRequest = new Gson().fromJson(request, UpdateBenhNhanRequest.class);
        return new ResponseEntity<>(service.updateBenhNhan(benhNhanRequest,file), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_LOGIN)
    public Object login(
            @RequestBody @Valid LoginRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_UPDATE_PASS)
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

    @PostMapping(value = Constants.URL_RESET_PASSWORD)
    public Object resetPassword(
            @RequestBody @Valid UpdatePassRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.resetPassword(request), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BENHNHAN)
    public Object getAllBenhNhan() throws ExceptionResponse {
        return new ResponseEntity<>(service.getAllBenhNhan(), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BENHNHAN_CHO)
    public Object getAllBenhNhanCho() throws ExceptionResponse {
        return new ResponseEntity<>(service.getAllBenhNhanCho(), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BENHNHAN_DIEU_TRI)
    public Object getAllBenhNhanDieuTri(
            @RequestHeader(value = "Authorization", required = false) String token
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.getAllBenhNhanDieuTri(), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> index(
    ) {
        return new ResponseEntity<>("Nguyễn Hồng Quân", HttpStatus.OK);
    }

}
