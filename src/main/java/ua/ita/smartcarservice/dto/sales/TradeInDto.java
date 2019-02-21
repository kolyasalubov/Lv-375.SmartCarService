package ua.ita.smartcarservice.dto.sales;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.UserDto;

/**
 * Created by 1 on 16.02.2019.
 */
public class TradeInDto {

    private Long id;

    private DealerDto dealerDto;

    private UserDto userDto;

    private CarDto usedCar;

    private  CarDto newCar;

    private String isActive;

    public TradeInDto(Long id, DealerDto dealerDto, UserDto userDto, CarDto usedCar, CarDto newCar, String isActive) {
        this.id = id;
        this.dealerDto = dealerDto;
        this.userDto = userDto;
        this.usedCar = usedCar;
        this.newCar = newCar;
        this.isActive = isActive;
    }

    public TradeInDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DealerDto getDealerDto() {
        return dealerDto;
    }

    public void setDealerDto(DealerDto dealerDto) {
        this.dealerDto = dealerDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public CarDto getUsedCar() {
        return usedCar;
    }

    public void setUsedCar(CarDto usedCar) {
        this.usedCar = usedCar;
    }

    public CarDto getNewCar() {
        return newCar;
    }

    public void setNewCar(CarDto newCar) {
        this.newCar = newCar;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
