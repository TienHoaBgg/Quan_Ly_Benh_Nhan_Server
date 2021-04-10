package com.quan.datn.controller;


import com.google.gson.Gson;
import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.database.NhanVien;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.service.NhanVienManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = Constants.URL_DELETE_NHAN_VIEN)
    public ResponseEntity deletePhongBenh(
            @RequestParam("manv") String maNV
    ) throws ExceptionResponse {
        return new ResponseEntity(service.deleteNhanVien(maNV),HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_NHAN_VIEN)
    public ResponseEntity getAllNhanVien() throws ExceptionResponse {
        return new ResponseEntity(service.getAllNhanVien(), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BAC_SI)
    public ResponseEntity getAllBacSi() throws ExceptionResponse{
        return new ResponseEntity(service.getAllBacSi(),HttpStatus.OK);
    }


}
