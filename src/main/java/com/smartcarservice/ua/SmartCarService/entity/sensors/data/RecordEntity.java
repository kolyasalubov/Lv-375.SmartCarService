package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@MappedSuperclass
public abstract class RecordEntity extends BaseRecordEntity{

    @Column(nullable = false)
    private BigDecimal value;

}
