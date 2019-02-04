package com.smartcarservice.ua.SmartCarService.Repository;

import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SalesManagerRepository extends JpaRepository<SalesManagerEntity, Long> {
    @Modifying
    @Query(value = "insert into sales_manager s(s.email, s.full_name, s.password, s.user_name) values(email,fullname,password,username)")
    @Transactional
    public void customSave(
            @Param("email") String email,
            @Param("fullname") String fullname,
            @Param("password") String password,
            @Param("username") String username);
//            @Param("dealer_id")Long dealer_id);


}
