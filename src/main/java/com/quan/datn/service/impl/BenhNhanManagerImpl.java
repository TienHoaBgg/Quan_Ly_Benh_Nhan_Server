package com.quan.datn.service.impl;

import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.BenhNhanManager;
import com.quan.datn.model.request.UpdateBenhNhanRequest;
import com.quan.datn.model.request.AddBenhNhanRequest;
import com.quan.datn.model.request.LoginRequest;
import com.quan.datn.model.request.UpdatePassRequest;
import com.quan.datn.service.BenhNhanManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BenhNhanManagerImpl implements BenhNhanManagerService {

    @Autowired
    private BenhNhanManager manager;

    @Override
    public Object login(LoginRequest request) throws ExceptionResponse {
        return manager.login(request);
    }

    @Override
    public Object getAllBenhNhanCho() throws ExceptionResponse {
        return manager.getAllBenhNhanCho();
    }

    @Override
    public Object getAllBenhNhanDieuTri() throws ExceptionResponse {
        return manager.getAllBenhNhanDieuTri();
    }

    @Override
    public Object getAllBenhNhan() throws ExceptionResponse {
        return manager.getAllBenhNhan();
    }

    @Override
    public Object updatePassword(UpdatePassRequest request) throws ExceptionResponse {
        return manager.updatePassword(request);
    }

    @Override
    public Object resetPassword(UpdatePassRequest request) throws ExceptionResponse {
        return manager.resetPassword(request);
    }

    @Override
    public Object updateBenhNhan(UpdateBenhNhanRequest request, MultipartFile file) throws ExceptionResponse {
        return manager.updateBenhNhan(request, file);
    }

    @Override
    public Object addBenhNhan(AddBenhNhanRequest request, MultipartFile file) throws ExceptionResponse {
        return manager.addBenhNhan(request,file);
    }

    @Override
    public Object deleteBenhNhan(String maBN) throws ExceptionResponse {
        return manager.deleteBenhNhan(maBN);
    }

    @Override
    public Object xuatVien(String maBN) throws ExceptionResponse {
        return manager.xuatVien(maBN);
    }

    @Override
    public Object nhapVien(String maBN) throws ExceptionResponse {
        return manager.nhapVien(maBN);
    }

    @Override
    public Object getInfoBenhNhan(String phoneNumber) throws ExceptionResponse {
        return manager.getInfoBenhNhan(phoneNumber);
    }

    @Override
    public Object getInfoByMabn(String maBN) throws ExceptionResponse {
        return manager.getInfoByMaBn(maBN);
    }

    @Override
    public Object checkPhoneNumber(String phoneNumber) throws ExceptionResponse {
        return manager.checkPhoneNumber(phoneNumber);
    }

}
