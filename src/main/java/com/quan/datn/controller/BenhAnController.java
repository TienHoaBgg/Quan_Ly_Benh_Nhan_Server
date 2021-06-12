package com.quan.datn.controller;


import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.DonThuocModel;
import com.quan.datn.model.database.BenhAn;
import com.quan.datn.service.BenhAnManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BenhAnController {

    @Autowired
    private BenhAnManagerService service;

    @PostMapping(value = Constants.URL_ADD_DON_THUOC)
    public ResponseEntity addDonThuoc(
            @RequestBody @Valid DonThuocModel request
    )throws ExceptionResponse {
        return new ResponseEntity(service.addDonThuoc(request),HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BENHAN)
    public ResponseEntity getAllBenhAn(
            @RequestParam(value = "mabn") String maBN
    )throws ExceptionResponse {
        return new ResponseEntity(service.getAllBenhAn(maBN), HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_GET_ALL_BENHAN_BY_PHONE)
    public ResponseEntity getAllBenhAnByPhoneNumber(
            @RequestParam(value = "phoneNumber") String phoneNumber
    )throws ExceptionResponse {
        return new ResponseEntity(service.getAllBenhAnByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_ADD_BENH_AN)
    public ResponseEntity addBenhAn(
            @RequestBody @Valid BenhAn request
            )throws ExceptionResponse {
        return new ResponseEntity(service.addBenhAn(request),HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_UPDATE_BENH_AN)
    public ResponseEntity updateBenhAn(
            @RequestBody @Valid BenhAn request
    )throws ExceptionResponse {
        return new ResponseEntity(service.updateBenhAn(request),HttpStatus.OK);
    }

}
