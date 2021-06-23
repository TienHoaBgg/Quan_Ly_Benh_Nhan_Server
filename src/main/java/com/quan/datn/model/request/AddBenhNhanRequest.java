package com.quan.datn.model.request;

import com.quan.datn.model.database.BenhAn;
import com.quan.datn.model.database.BenhNhan;

public class AddBenhNhanRequest {
    private String maBN;
    private String hoBn;
    private String tenBn;
    private String gioiTinh;
    private String ngaySinh;
    private String cmnd;
    private String ngheNghiep;
    private String diaChi;
    private String sdt;
    private String publicKey;
    private BenhAn benhAn;

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getHoBn() {
        return hoBn;
    }

    public void setHoBn(String hoBn) {
        this.hoBn = hoBn;
    }

    public String getTenBn() {
        return tenBn;
    }

    public void setTenBn(String tenBn) {
        this.tenBn = tenBn;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public BenhAn getBenhAn() {
        return benhAn;
    }

    public void setBenhAn(BenhAn benhAn) {
        this.benhAn = benhAn;
    }

    public BenhNhan getBenhNhan(){
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setTrangThai("Đang chờ khám");
        benhNhan.setMaBN(this.getMaBN());
        benhNhan.setHo(this.getHoBn());
        benhNhan.setTen(this.getTenBn());
        benhNhan.setGioiTinh(this.getGioiTinh());
        benhNhan.setNgaySinh(this.getNgaySinh());
        benhNhan.setCmnd(this.getCmnd());
        benhNhan.setNgheNghiep(this.getNgheNghiep());
        benhNhan.setDiaChi(this.getDiaChi());
        benhNhan.setSdt(this.getSdt());
        benhNhan.setPublicKey(this.getPublicKey());
        return benhNhan;
    }

}
