package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sales.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesManagerRepository extends JpaRepository<SalesManager, Long> {
    @Query(value = "from SalesManager s inner join fetch s.dealer where s.userName = :userName")
    List<SalesManager> findByUserName(@Param("userName") String userName);

    SalesManager getByUserName(String username);

    List<SalesManager> findAll();

    SalesManager findSalesManagerByUserName(String username);

    SalesManager getSalesManagerById(Long id);

    @Query(value = "select s from SalesManager as s inner join fetch Dealer as d on s.dealer=d.id where s.dealer =:username")
    List<SalesManager> findAlByDealer_UserName(String username);

    void deleteByUserName(String username);

    SalesManager getSalesManagerByUserName(String username);


}
