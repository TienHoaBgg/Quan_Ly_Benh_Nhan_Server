package com.quan.datn.repository;


import com.quan.datn.model.database.BaoCao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaoCaoRepository extends JpaRepository<BaoCao,Integer> {


    @Query(nativeQuery = true,value = "SELECT * FROM BAOCAO WHERE THOIGIAN BETWEEN :startTime AND :endTime")
    List<BaoCao> findAllByThoiGian(
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );

    @Query(nativeQuery = true,value = "SELECT * FROM BAOCAO WHERE THOIGIAN >= :startTime")
    List<BaoCao> findAllByStartTime(
            @Param("startTime") String startTime
    );

    @Query(nativeQuery = true,value = "SELECT * FROM BAOCAO WHERE THOIGIAN <= :endTime")
    List<BaoCao> findAllByEndTime(
            @Param("endTime") String endTime
    );

}
