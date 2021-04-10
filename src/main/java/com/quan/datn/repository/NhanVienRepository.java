package com.quan.datn.repository;


import com.quan.datn.model.database.BenhAn;
import com.quan.datn.model.database.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM NHANVIEN WHERE MANV LIKE :maNV")
    NhanVien findByMaNV(
            @Param("maNV") String maNV
    );

    @Query(nativeQuery = true, value = "SELECT * FROM NHANVIEN WHERE VITRI like  'Bác Sĩ'")
    List<NhanVien> getAllBacSi();

}
