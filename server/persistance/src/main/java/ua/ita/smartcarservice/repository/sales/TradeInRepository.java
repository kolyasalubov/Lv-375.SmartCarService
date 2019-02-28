package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.TradeIn;

/**
 * Created by 1 on 27.02.2019.
 */
public interface TradeInRepository extends JpaRepository<TradeIn,Long> {




//    List<TradeIn>findAllByDealerEntity(DealerEntity dealerEntity);




}