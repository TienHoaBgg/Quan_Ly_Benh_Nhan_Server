package com.quan.datn.common;

public interface Constants {
    String KEY_TOKEN = "NguyenHongQuan";

    String API = "/api";
    String BENHNHAN = "/benhnhan";
    String NHANVIEN = "/nhanvien";
    String PHONGBENH = "/phongbenh";
    String PHONG_KHAM = "/phongkham";
    String BENHAN = "/benhan";
    String BACSI = "/bacsi";
    String DON_THUOC = "/donthuoc";
    String BAO_CAO = "/baocao";

    String ALL = "/all";
    String DELETE = "/delete";
    String ADD = "/add";
    String RESET = "/reset";
    String UPDATE = "/update";


    ///==================================================
    /// Benh nhan
    String LOGIN = "/login";
    String UPDATE_PASS = "/updatePass";
    String GET_BENHNHAN_CHO = "/getAllBenhNhanCho";
    String GET_BENHNHAN_DIEU_TRI = "/getAllBenhNhanDieuTri";
    String XUAT_VIEN = "/xuatvien";
    String NHAP_VIEN = "/nhapvien";
    String URL_GET_ALL_BENHNHAN_DIEU_TRI = API + BENHNHAN + GET_BENHNHAN_DIEU_TRI;
    String URL_GET_ALL_BENHNHAN_CHO = API + BENHNHAN + GET_BENHNHAN_CHO;
    String URL_GET_ALL_BENHNHAN = API + BENHNHAN + ALL;
    String URL_LOGIN = API + BENHNHAN + LOGIN;
    String URL_UPDATE_PASS = API + BENHNHAN + UPDATE_PASS;
    String URL_RESET_PASSWORD = API + BENHNHAN + RESET;
    String URL_UPDATE_BENHNHAN = API + BENHNHAN + UPDATE;
    String URL_ADD_BENH_NHAN = API + BENHNHAN + ADD;
    String URL_DELETE_BENHNHAN = API + BENHNHAN + DELETE;
    String URL_BENH_NHAN_XUAT_VIEN = API + BENHNHAN + XUAT_VIEN;
    String URL_BENH_NHAN_NHAP_VIEN = API + BENHNHAN + NHAP_VIEN;


    ///==================================================
    // Phong Kham
    String URL_GET_ALL_PHONG_KHAM = API + PHONG_KHAM + ALL;
    String URL_DELETE_PHONG_KHAM = API + PHONG_KHAM + DELETE;
    String URL_ADD_PHONG_KHAM = API + PHONG_KHAM + ADD;


    ///==================================================
    /// Benh an
    String URL_GET_ALL_BENHAN = API + BENHAN + ALL;
    String URL_ADD_BENH_AN = API + BENHAN + ADD;
    String URL_UPDATE_BENH_AN = API + BENHAN + UPDATE;

    ///==================================================
    /// Don Thuoc
    String URL_ADD_DON_THUOC = API + DON_THUOC + ADD;


    ///==================================================
    /// Phong Benh
    String URL_GET_ALL_PHONG_BENH = API + PHONGBENH + ALL;
    String URL_DELETE_PHONG_BENH = API + PHONGBENH + DELETE;
    String URL_ADD_PHONG_BENH = API + PHONGBENH + ADD;


    ///===================================================
    // Nhan Vien
    String URL_GET_ALL_NHAN_VIEN = API + NHANVIEN + ALL;
    String URL_GET_ALL_BAC_SI = API + NHANVIEN + ALL + BACSI;
    String URL_DELETE_NHAN_VIEN = API + NHANVIEN + DELETE;
    String URL_UPDATE_NHAN_VIEN = API + NHANVIEN + UPDATE;
    String URL_ADD_NHAN_VIEN = API + NHANVIEN + ADD;

    ///===================================================
    // Bao Cao
    String URL_GET_ALL_BAO_CAO = API + BAO_CAO + ALL;



}
