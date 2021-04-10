package com.quan.datn.common;

import org.springframework.http.HttpStatus;


public class CommonResponse {
    private int status;
    private String message;
    private Object data;

    public CommonResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResponse(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public CommonResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResponse(HttpStatus status, Object data) {
        this.status = status.value();
        this.message = MessageResponse.SUCCESS;
        this.data = data;
    }

    public CommonResponse(int status, Object data) {
        this.status = status;
        this.message = "";
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
