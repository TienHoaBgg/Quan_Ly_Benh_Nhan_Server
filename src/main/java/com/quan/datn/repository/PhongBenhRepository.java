package com.quan.datn.repository;


import com.quan.datn.model.database.PhongBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongBenhRepository extends JpaRepository<PhongBenh,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM PHONGBENH WHERE MAPB LIKE :maPB")
    PhongBenh findByMaPB(
            @Param("maPB") String maPB
    );

}
