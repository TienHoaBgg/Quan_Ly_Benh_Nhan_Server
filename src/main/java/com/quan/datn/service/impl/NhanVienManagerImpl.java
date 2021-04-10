package com.quan.datn.service.impl;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.NhanVienManager;
import com.quan.datn.model.database.NhanVien;
import com.quan.datn.service.NhanVienManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NhanVienManagerImpl implements NhanVienManagerService {

    @Autowired
    private NhanVienManager manager;


    @Override
    public Object getAllNhanVien() throws ExceptionResponse {
        return manager.getAllNhanVien();
    }

    @Override
    public Object getAllBacSi() throws ExceptionResponse {
        return manager.getAllBacSi();
    }

    @Override
    public Object deleteNhanVien(String maNV) throws ExceptionResponse {
        return manager.deleteNhanVien(maNV);
    }

    @Override
    public Object updateNhanVien(NhanVien updateRequest, MultipartFile file) throws ExceptionResponse {
        return manager.updateNhanVien(updateRequest,file);
    }

    @Override
    public Object addNhanVien(NhanVien addRequest, MultipartFile file) throws ExceptionResponse {
        return manager.addNhanVien(addRequest,file);
    }
}
