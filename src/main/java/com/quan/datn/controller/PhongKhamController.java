package com.quan.datn.controller;


import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.AddPhongBenhRequest;
import com.quan.datn.model.request.AddPhongKhamRequest;
import com.quan.datn.service.PhongKhamManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PhongKhamController {

    @Autowired
    private PhongKhamManagerService service;

    @GetMapping(value = Constants.URL_GET_ALL_PHONG_KHAM)
    public Object getAllPhongKham() throws ExceptionResponse {
        return new ResponseEntity<>(service.getAllPhongKham(), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_ADD_PHONG_KHAM)
    public Object addPhongKham(
            @RequestBody @Valid AddPhongKhamRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.addPhongKham(request),HttpStatus.OK);
    }

    @GetMapping(value = Constants.URL_DELETE_PHONG_KHAM)
    public Object deletePhongKham(
            @RequestParam("mapk") String maPK
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.deletePhongKham(maPK),HttpStatus.OK);
    }

}
