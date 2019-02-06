package com.smartcarservice.ua.SmartCarService.entity.sales;


import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "sales_manager")
public class SalesManagerEntity extends UserBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;

    public SalesManagerEntity(String email, String password, String fullName, String userName,Dealer dealer) {
        this.dealer = dealer;

        this.setEmail(email);
        this.setPassword(password);
        this.setFullName(fullName);
        this.setUserName(userName);
    }
}
