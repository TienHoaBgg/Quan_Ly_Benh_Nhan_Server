package com.quan.datn.service.impl;


import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.manager.BaoCaoManager;
import com.quan.datn.service.BaoCaoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaoCaoManagerImpl implements BaoCaoManagerService {

    @Autowired
    private BaoCaoManager manager;


    @Override
    public Object getBaoCao(String startTime, String endTime) throws ExceptionResponse {
        return manager.getBaoCao(startTime,endTime);
    }
}
