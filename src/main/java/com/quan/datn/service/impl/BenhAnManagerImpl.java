package com.quan.datn.service.impl;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.BenhAnManager;
import com.quan.datn.model.DonThuocModel;
import com.quan.datn.model.database.BenhAn;
import com.quan.datn.service.BenhAnManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenhAnManagerImpl implements BenhAnManagerService {

    @Autowired
    private BenhAnManager manager;

    @Override
    public Object getAllBenhAn(String maBN) throws ExceptionResponse {
        return manager.getAllBenhAn(maBN);
    }

    @Override
    public Object addBenhAn(BenhAn request) throws ExceptionResponse {
        return manager.addBenhAn(request);
    }

    @Override
    public Object addDonThuoc(DonThuocModel request) throws ExceptionResponse {
        return manager.addDonThuoc(request);
    }

    @Override
    public Object updateBenhAn(BenhAn request) throws ExceptionResponse {
        return manager.updateBenhAn(request);
    }

    @Override
    public Object getAllBenhAnByPhoneNumber(String phoneNumber) throws ExceptionResponse {
        return manager.getAllBenhAnByPhoneNumber(phoneNumber);
    }
}
