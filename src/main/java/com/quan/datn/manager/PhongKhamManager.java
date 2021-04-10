package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.model.database.PhongBenh;
import com.quan.datn.model.database.PhongKham;
import com.quan.datn.model.request.AddPhongKhamRequest;
import com.quan.datn.model.response.PhongKhamResponse;
import com.quan.datn.repository.NhanVienRepository;
import com.quan.datn.repository.PhongKhamRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static com.quan.datn.common.MessageResponse.*;


@Controller
public class PhongKhamManager {

    @Autowired
    private PhongKhamRepository phongKhamRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public Object getAllPhongKham() throws ExceptionResponse {
        List<PhongKham> phongKhams = phongKhamRepository.findAll();
        if (phongKhams.size() == 0){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        List<PhongKhamResponse> responses = phongKhams.stream().map((phongKham) ->{
            PhongKhamResponse response = new PhongKhamResponse();
            response.setId(phongKham.getId());
            response.setMaPK(phongKham.getMaPK());
            response.setTenPK(phongKham.getTenPK());
            response.setBacSi(nhanVienRepository.findByMaNV(phongKham.getMaBS()));
            return response;
        }).collect(Collectors.toList());

        return new CommonResponse(HttpStatus.OK,responses);
    }

    public Object deletePhongKham(String maPK) throws ExceptionResponse {
        PhongKham phongKham = phongKhamRepository.findByMaPK(maPK);
        if (phongKham == null){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, VALUES_NOT_EXITS);
        }
        try {
            phongKhamRepository.delete(phongKham);
            return new CommonResponse(HttpStatus.OK,SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
    }

    public Object addPhongKham(AddPhongKhamRequest request) throws ExceptionResponse {
        try {
            phongKhamRepository.save(request.getPhongKham());
            return new CommonResponse(HttpStatus.OK,SUCCESS);
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST,AN_UNKNOWN_ERROR);
        }


    }
}
