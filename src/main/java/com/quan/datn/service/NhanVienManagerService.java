package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.database.NhanVien;
import org.springframework.web.multipart.MultipartFile;

public interface NhanVienManagerService {


    Object getAllNhanVien() throws ExceptionResponse;

    Object getAllBacSi() throws ExceptionResponse;

    Object deleteNhanVien(String maNV) throws ExceptionResponse;

    Object updateNhanVien(NhanVien updateRequest, MultipartFile file) throws ExceptionResponse;

    Object addNhanVien(NhanVien addRequest, MultipartFile file) throws ExceptionResponse;
}
