package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.DonThuocModel;
import com.quan.datn.model.database.BenhAn;

public interface BenhAnManagerService {

    Object getAllBenhAn(String maBN) throws ExceptionResponse;

    Object addBenhAn(BenhAn request) throws ExceptionResponse;

    Object addDonThuoc(DonThuocModel request) throws ExceptionResponse;

    Object updateBenhAn(BenhAn request) throws ExceptionResponse;
}
