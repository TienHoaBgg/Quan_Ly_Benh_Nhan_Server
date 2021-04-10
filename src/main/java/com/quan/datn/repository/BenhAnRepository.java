package com.quan.datn.repository;


import com.quan.datn.model.database.BenhAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenhAnRepository extends JpaRepository<BenhAn,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM BENHAN WHERE MABA LIKE :maBA")
    BenhAn findByMaBA(
        @Param("maBA") String maBA
    );

    @Query(nativeQuery = true, value = "SELECT * FROM benhan WHERE MABN LIKE :maBN ORDER BY THOIGIANKHAM DESC")
    List<BenhAn> findAllByMaBN(
            @Param("maBN") String maBN
    );

    @Query(nativeQuery = true, value = "SELECT * FROM benhan WHERE MABN LIKE :maBN ORDER BY THOIGIANKHAM DESC LIMIT 1")
    BenhAn findOneByMaBN(
            @Param("maBN") String maBN
    );

}
