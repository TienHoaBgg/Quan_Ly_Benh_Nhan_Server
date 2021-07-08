package com.quan.datn.controller;

import com.quan.datn.common.Constants;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.AddPhongBenhRequest;
import com.quan.datn.service.PhongBenhManagaerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PhongBenhController {

    @Autowired
    private PhongBenhManagaerService service;

    @GetMapping(value = Constants.URL_GET_ALL_PHONG_BENH)
    public Object getAllPhongBenh() throws ExceptionResponse {
        return new ResponseEntity<>(service.getALlPhongBenh(), HttpStatus.OK);
    }

    @PostMapping(value = Constants.URL_ADD_PHONG_BENH)
    public Object addPhongBenh(
            @RequestBody @Valid AddPhongBenhRequest request
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.addPhongBenh(request),HttpStatus.OK);
    }

    @DeleteMapping(value = Constants.URL_DELETE_PHONG_BENH)
    public Object deletePhongBenh(
            @RequestParam("mapb") String maPB
    ) throws ExceptionResponse {
        return new ResponseEntity<>(service.deletePhongBenh(maPB),HttpStatus.OK);
    }

}
