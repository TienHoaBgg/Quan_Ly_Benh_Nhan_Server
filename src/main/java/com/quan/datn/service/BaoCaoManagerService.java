package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;

public interface BaoCaoManagerService {


    Object getBaoCao(String startTime, String endTime) throws ExceptionResponse;
}
