package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
public interface TradeInService {

    void createTradeIn(CreateTraidInDto traidInDto);

}
