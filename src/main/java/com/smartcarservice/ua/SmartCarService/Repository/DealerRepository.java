package com.smartcarservice.ua.SmartCarService.Repository;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    public Dealer getDealerByUserName(String username);
}
