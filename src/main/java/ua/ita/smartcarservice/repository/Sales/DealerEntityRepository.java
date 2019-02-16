package ua.ita.smartcarservice.repository.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

/**
 * Created by 1 on 10.02.2019.
 */
public interface DealerEntityRepository extends JpaRepository<DealerEntity,Long> {

DealerEntity findByUserEntity_Username(String username);

DealerEntity findByDealerEdr(String edr);
}
