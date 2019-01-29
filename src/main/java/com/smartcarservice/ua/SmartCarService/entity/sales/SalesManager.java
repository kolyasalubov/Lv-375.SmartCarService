package com.smartcarservice.ua.SmartCarService.entity.sales;


import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sales_manager")
public class SalesManager extends UserBaseEntity {



    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private Dealer dealer;
}
