package com.quan.datn.repository;

import com.quan.datn.model.database.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM BENHNHAN  WHERE TRANGTHAI LIKE 'Đang điều trị'")
    List<BenhNhan> findAllByBenhNhanDieuTri();

    @Query(nativeQuery = true, value = "SELECT * FROM BENHNHAN  WHERE TRANGTHAI LIKE 'Đang chờ khám'")
    List<BenhNhan> findAllByBenhNhanCho();

    @Query(nativeQuery = true, value = "SELECT * FROM BENHNHAN")
    List<BenhNhan> findAllBenhNhan();

    @Query(nativeQuery = true, value = "SELECT * FROM BENHNHAN  WHERE SDT LIKE :SDT")
    BenhNhan findByPhoneNumber(
            @Param("SDT") String SDT
    );

    @Query(nativeQuery = true, value = "SELECT * FROM BENHNHAN  WHERE MaBN LIKE :MaBN")
    BenhNhan findByMaBN(
            @Param("MaBN") String MaBN
    );

}
