package com.quan.datn.controller;


import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.common.MessageResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionController implements MessageResponse {

    @ExceptionHandler(ExceptionResponse.class)
    public final ResponseEntity handleUserNotFoundException(HttpServletRequest req, ExceptionResponse ex) {
        CommonResponse response = new CommonResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public final ResponseEntity handleNumberFormatException(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        CommonResponse response = new CommonResponse(HttpStatus.CONFLICT, FORMAT_VALUE_INVALID);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException manve) {
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            System.out.println(fieldError.getField());
            CommonResponse response = new CommonResponse(HttpStatus.NOT_FOUND, fieldError.getField() + " is required");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        CommonResponse response = new CommonResponse(HttpStatus.FORBIDDEN, "Error param");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity<CommonResponse> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        CommonResponse response = new CommonResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> exceptionHandler(HttpServletRequest req, Exception ex) {
        CommonResponse response = new CommonResponse(HttpStatus.FORBIDDEN, "The request could not be understood by the server due to malformed syntax.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse> httpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        CommonResponse response = new CommonResponse(HttpStatus.NOT_FOUND, ex.getMessage() + " (Input data invalid)");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse> httpDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException ex) {
        CommonResponse response = new CommonResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<CommonResponse> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new ResponseEntity<>(new CommonResponse(HttpStatus.EXPECTATION_FAILED,"One or more files are too large!"),HttpStatus.EXPECTATION_FAILED);
    }
}

