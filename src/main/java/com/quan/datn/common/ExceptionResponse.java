package com.quan.datn.common;


import org.springframework.http.HttpStatus;


public class ExceptionResponse extends Exception{
    private int status;
    private String message;


    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ExceptionResponse(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
    }

    public int getStatus() {
        return status;
    }

}
