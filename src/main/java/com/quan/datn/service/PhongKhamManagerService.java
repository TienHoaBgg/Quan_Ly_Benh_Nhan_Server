package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.AddPhongKhamRequest;

public interface PhongKhamManagerService {

    Object getAllPhongKham() throws ExceptionResponse;


    Object deletePhongKham(String maPK) throws ExceptionResponse;

    Object addPhongKham(AddPhongKhamRequest request) throws ExceptionResponse;
}
