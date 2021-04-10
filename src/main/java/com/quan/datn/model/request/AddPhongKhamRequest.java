package com.quan.datn.model.request;


import com.quan.datn.model.database.PhongKham;

public class AddPhongKhamRequest {
    private String maPK;
    private String tenPK;
    private String maBS;

    public String getMaPK() {
        return maPK;
    }

    public void setMaPK(String maPK) {
        this.maPK = maPK;
    }

    public String getTenPK() {
        return tenPK;
    }

    public void setTenPK(String tenPK) {
        this.tenPK = tenPK;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public PhongKham getPhongKham(){
        PhongKham phongKham = new PhongKham();
        phongKham.setTenPK(this.getTenPK());
        phongKham.setMaPK(this.getMaPK());
        phongKham.setMaBS(this.getMaBS());
        return phongKham;
    }

}
