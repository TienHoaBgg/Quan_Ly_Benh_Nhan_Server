package com.quan.datn.repository;


import com.quan.datn.model.database.DonThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonThuocRepository extends JpaRepository<DonThuoc,Integer> {

    @Query(nativeQuery = true,value = " SELECT * FROM DONTHUOC WHERE MABA LIKE :MaBA")
    List<DonThuoc> getDonThuocByMaBA(
            @Param("MaBA") String MaBA
    );


}
