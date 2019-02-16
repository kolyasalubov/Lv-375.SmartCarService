package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.sales.*;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */

public interface DealerService {

void createDealer(DealerEntity dealerEntity, String username);

DealerEntity getDealerEntityByUserName(String Username);



List<DealerEntity> getAllDealers();

DealerDto getDealerDtoByUserName(String username);

DealerEntity dealerDtoToEntity(DealerDto dealerDto);

void editDealerEntityByEntity(DealerEntity dealerEntity);

void editDealerByDealerDto(DealerDto dealerDto);

List<DealerDto> getAllDealerDto();





}
