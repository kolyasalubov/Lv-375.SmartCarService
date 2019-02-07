package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Query(value = "from SalesManager s inner join fetch s.dealer where s.userName = :userName")
    List<SalesManager> findByUserName(@Param("userName") String userName);
    SalesManager getByUserName(String username);
    List<SalesManager> findAll();
    SalesManager findSalesManagerByUserName(String username);
    SalesManager getSalesManagerById(Long id);
//    "select w from Worker as w LEFT JOIN Skill as s on w.skill = s.skillId where s.name = :name and w.technicalService = :stoI""
    @Query(value = "select s from SalesManager as s inner join fetch Dealer as d on s.dealer=d.id where s.dealer =:username")
    List<SalesManager> findAlByDealer_UserName(String username);
//    List<SalesManager> getAllByDealerAllByDealer_UserName(String username);
    void deleteByUserName(String username);
    SalesManager getSalesManagerByUserName(String username);




}
