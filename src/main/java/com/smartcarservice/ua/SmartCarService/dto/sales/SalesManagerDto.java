package com.smartcarservice.ua.SmartCarService.dto.sales;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesManagerDto {
    private String email;
    private String password;
    private String fullName;
    private String userName;
    private String dealerUsername;
}
