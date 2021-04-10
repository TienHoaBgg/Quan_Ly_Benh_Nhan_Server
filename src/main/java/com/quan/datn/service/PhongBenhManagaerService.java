package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.AddPhongBenhRequest;

public interface PhongBenhManagaerService {

    Object getALlPhongBenh() throws ExceptionResponse;


    Object deletePhongBenh(String maPB) throws ExceptionResponse;

    Object addPhongBenh(AddPhongBenhRequest request) throws ExceptionResponse;
}
