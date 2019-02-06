package com.smartcarservice.ua.SmartCarService.Repository;

import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesManagerRepository extends JpaRepository<SalesManager, Long> {
//    @Modifying
//    @Query(value = "insert into sales_manager s(s.email, s.full_name, s.password, s.user_name) values(email,fullname,password,username)")
//    @Transactional
//    public void customSave(
//            @Param("email") String email,
//            @Param("fullname") String fullname,
//            @Param("password") String password,
//            @Param("username") String username);
////            @Param("dealer_id")Long dealer_id);
    SalesManager findByUserName(String username);

}
