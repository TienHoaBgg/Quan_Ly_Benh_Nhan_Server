package com.quan.datn.service.impl;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.PhongKhamManager;
import com.quan.datn.model.request.AddPhongKhamRequest;
import com.quan.datn.service.PhongKhamManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhongKhamManagerImpl implements PhongKhamManagerService {
    @Autowired
    private PhongKhamManager manager;

    @Override
    public Object getAllPhongKham() throws ExceptionResponse {
        return manager.getAllPhongKham();
    }

    @Override
    public Object deletePhongKham(String maPK) throws ExceptionResponse {
        return manager.deletePhongKham(maPK);
    }

    @Override
    public Object addPhongKham(AddPhongKhamRequest request) throws ExceptionResponse {
        return manager.addPhongKham(request);
    }
}
