package com.smartcarservice.ua.SmartCarService.entity;


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
