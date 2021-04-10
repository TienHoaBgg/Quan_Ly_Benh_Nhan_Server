package com.quan.datn.service;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.request.AddBenhNhanRequest;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import org.springframework.web.multipart.MultipartFile;

public interface BenhNhanManagerService {

    Object login(LoginRequest request) throws ExceptionResponse;

    Object getAllBenhNhanCho() throws ExceptionResponse;

    Object getAllBenhNhanDieuTri() throws ExceptionResponse;

    Object getAllBenhNhan() throws ExceptionResponse;

    Object updatePassword(UpdatePassRequest request) throws ExceptionResponse;

    Object resetPassword(UpdatePassRequest request) throws ExceptionResponse;

    Object updateBenhNhan(UpdateBenhNhanRequest request, MultipartFile file) throws ExceptionResponse;

    Object addBenhNhan(AddBenhNhanRequest request, MultipartFile file) throws ExceptionResponse;

    Object deleteBenhNhan(String maBN) throws ExceptionResponse;

    Object xuatVien(String maBN) throws ExceptionResponse;

    Object nhapVien(String maBN) throws ExceptionResponse;
}
