package ua.ita.smartcarservice.repository.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

/**
 * Created by 1 on 10.02.2019.
 */
public interface SaleManagerRepository extends JpaRepository<UserSaleManager,Long> {
}
