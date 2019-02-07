package ua.ita.smartcarservice.dto.saleDto;

import lombok.Data;
import ua.ita.smartcarservice.dto.stoDto.CarOwnerDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.car.CarOwner;
import ua.ita.smartcarservice.entity.sales.SalesManager;

/**
 * Created by 1 on 08.02.2019.
 */
@Data
public class TradeinDto {

   private CarOwnerDto carOwnerDto;

   private SalesManager salesManager;

   private Car carovnercar;

   private Car newcar;

    public TradeinDto(CarOwnerDto carOwnerDto, SalesManager salesManager, Car carovnercar, Car newcar) {
        this.carOwnerDto = carOwnerDto;
        this.salesManager = salesManager;
        this.carovnercar = carovnercar;
        this.newcar = newcar;
    }

    public CarOwnerDto getCarOwnerDto() {
        return carOwnerDto;
    }

    public void setCarOwnerDto(CarOwnerDto carOwnerDto) {
        this.carOwnerDto = carOwnerDto;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(SalesManager salesManager) {
        this.salesManager = salesManager;
    }

    public Car getCarovnercar() {
        return carovnercar;
    }

    public void setCarovnercar(Car carovnercar) {
        this.carovnercar = carovnercar;
    }

    public Car getNewcar() {
        return newcar;
    }

    public void setNewcar(Car newcar) {
        this.newcar = newcar;
    }
}
