package ua.ita.smartcarservice.repository.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.UserDealer;

/**
 * Created by 1 on 10.02.2019.
 */
public interface DealerRepository extends JpaRepository<UserDealer,Long> {




}
