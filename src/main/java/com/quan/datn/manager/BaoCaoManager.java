package com.quan.datn.manager;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.common.MessageResponse;
import com.quan.datn.model.database.BaoCao;
import com.quan.datn.repository.BaoCaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BaoCaoManager {

    @Autowired
    private BaoCaoRepository baoCaoRepository;

    public Object getBaoCao(String startTime, String endTime) throws ExceptionResponse {
        List<BaoCao> response;
        if (startTime.isEmpty() && endTime.isEmpty()){
            response = baoCaoRepository.findAll();
        }else if (!startTime.isEmpty() && endTime.isEmpty()){
            response = baoCaoRepository.findAllByStartTime(startTime);
        }else if (startTime.isEmpty() && !endTime.isEmpty()){
            response = baoCaoRepository.findAllByEndTime(endTime);
        }else{
            response = baoCaoRepository.findAllByThoiGian(startTime,endTime);
        }
        if (response.isEmpty()){
            throw new ExceptionResponse(HttpStatus.FORBIDDEN, MessageResponse.VALUES_NOT_EXITS);
        }
        return new CommonResponse(HttpStatus.OK,response);
    }
}
