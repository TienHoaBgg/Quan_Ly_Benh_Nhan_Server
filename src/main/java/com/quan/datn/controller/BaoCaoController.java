package com.quan.datn.controller;


import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.service.BaoCaoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController public class BaoCaoController {

    @Autowired
    private BaoCaoManagerService service;

    @GetMapping(value = Constants.URL_GET_ALL_BAO_CAO)
    public ResponseEntity getBaoCao(
            @RequestParam(required = false, name = "startTime" , defaultValue = "") String startTime,
            @RequestParam(required = false, name = "endTime", defaultValue = "") String endTime
    ) throws ExceptionResponse {
        return new ResponseEntity(service.getBaoCao(startTime,endTime),HttpStatus.OK);
    }


}
