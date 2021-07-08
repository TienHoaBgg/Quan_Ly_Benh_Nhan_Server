package com.quan.datn.model.database;


import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;

@Entity(name = "donthuoc")
public class DonThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MABA")
    private String maBA;

    @Column(name = "TENTHUOC")
    private String tenThuoc;

    @Column(name = "SOLUONG")
    private int soLuong;

    @Column(name = "DONGIA")
    private float donGia;

    @Column(name = "THANHTIEN")
    private float thanhTien;

    @Column(name = "HUONGDAN")
    private String huongDan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MABA", referencedColumnName = "MABA", insertable = false, updatable = false)
    private BenhAn benhAn;

    public BenhAn getBenhAn() {
        return benhAn;
    }

    public void setBenhAn(BenhAn benhAn) {
        this.benhAn = benhAn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaBA() {
        return maBA;
    }

    public void setMaBA(String maBA) {
        this.maBA = maBA;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getHuongDan() {
        return huongDan;
    }

    public void setHuongDan(String huongDan) {
        this.huongDan = huongDan;
    }
}
