package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import lombok.Data;

@Data
public class TechnicalServiceDto {

    private String name;

    private String address;

    private Long dealerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }
}
