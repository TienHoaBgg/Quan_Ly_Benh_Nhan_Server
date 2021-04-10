package com.quan.datn.repository;


import com.quan.datn.model.database.PhongBenh;
import com.quan.datn.model.database.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongKhamRepository extends JpaRepository<PhongKham,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM PHONGKHAM WHERE MAPK LIKE :maPK")
    PhongKham findByMaPK(
            @Param("maPK") String maPK
    );

}
