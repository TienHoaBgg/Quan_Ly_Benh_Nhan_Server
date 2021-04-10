package com.quan.datn.service.impl;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.PhongBenhManager;
import com.quan.datn.model.request.AddPhongBenhRequest;
import com.quan.datn.service.PhongBenhManagaerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhongBenhManagerImpl implements PhongBenhManagaerService {

    @Autowired
    private PhongBenhManager manager;


    @Override
    public Object getALlPhongBenh() throws ExceptionResponse {
        return manager.getAllPhongBenh();
    }

    @Override
    public Object deletePhongBenh(String maPB) throws ExceptionResponse {
        return manager.deletePhongBenh(maPB);
    }

    @Override
    public Object addPhongBenh(AddPhongBenhRequest request) throws ExceptionResponse {
        return manager.addPhongBenh(request);
    }
}
