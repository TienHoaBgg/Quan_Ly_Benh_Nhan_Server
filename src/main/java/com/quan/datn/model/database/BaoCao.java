package com.quan.datn.model.database;

import com.quan.datn.common.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity(name = "baocao")
public class BaoCao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String maBN;
    private String hoTen;
    private String diaChi;
    private String ngheNghiep;
    private String sdt;
    private String noiDung;
    private String thoiGian;

    public BaoCao() {
    }

    public BaoCao(BenhNhan benhNhan) {
        this.maBN  = benhNhan.getMaBN();
        this.hoTen = benhNhan.getHo() + " " + benhNhan.getTen();
        this.diaChi = benhNhan.getDiaChi();
        this.ngheNghiep = benhNhan.getNgheNghiep();
        this.sdt = benhNhan.getSdt();
        this.setThoiGian(Utils.getCurrentTimeStamp());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
