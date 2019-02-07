package ua.ita.smartcarservice.dto.saleDto;

import lombok.Data;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.car.CarOwner;
import ua.ita.smartcarservice.entity.sales.SalesManager;

/**
 * Created by 1 on 08.02.2019.
 */
@Data
public class TradeinDto {

   private CarOwner carOwner;

   private SalesManager salesManager;

   private Car carovnercar;

   private Car newcar;

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
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
