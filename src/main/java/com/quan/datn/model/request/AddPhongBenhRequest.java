package com.quan.datn.model.request;


import com.quan.datn.model.database.PhongBenh;

public class AddPhongBenhRequest {
    private String maPB;
    private String tenPB;

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public PhongBenh getPhongBenh(){
        PhongBenh phongBenh = new PhongBenh();
        phongBenh.setMaPB(this.getMaPB());
        phongBenh.setTenPB(this.getTenPB());
        return phongBenh;
    }
}
