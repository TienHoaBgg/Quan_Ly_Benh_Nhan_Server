package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.database.PhongBenh;
import com.quan.datn.model.request.AddPhongBenhRequest;
import com.quan.datn.repository.PhongBenhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.quan.datn.common.MessageResponse.*;

@Controller
public class PhongBenhManager {

    @Autowired
    private PhongBenhRepository phongBenhRepository;

    public Object getAllPhongBenh() throws ExceptionResponse {
        List<PhongBenh> dsPhongBenh = phongBenhRepository.findAll();
        if (dsPhongBenh.size() <= 0){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK,dsPhongBenh);
    }

    public Object deletePhongBenh(String maPB) throws ExceptionResponse{
        PhongBenh phongBenh = phongBenhRepository.findByMaPB(maPB);
        if (phongBenh == null){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        try {
            phongBenhRepository.delete(phongBenh);
            return new CommonResponse(HttpStatus.OK,SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
        }
    }
    
    public Object addPhongBenh(AddPhongBenhRequest request) throws ExceptionResponse{
        try {
            phongBenhRepository.save(request.getPhongBenh());
            return new CommonResponse(HttpStatus.OK,SUCCESS);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
        }
    }
}
