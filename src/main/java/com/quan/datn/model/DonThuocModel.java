package com.quan.datn.model;


import com.quan.datn.model.database.DonThuoc;

import java.util.List;

public class DonThuocModel {
    private String maBA;
    private List<DonThuoc> donthuocs;

    public String getMaBA() {
        return maBA;
    }

    public void setMaBA(String maBA) {
        this.maBA = maBA;
    }

    public List<DonThuoc> getDonthuocs() {
        return donthuocs;
    }

    public void setDonthuocs(List<DonThuoc> donthuocs) {
        this.donthuocs = donthuocs;
    }
}
