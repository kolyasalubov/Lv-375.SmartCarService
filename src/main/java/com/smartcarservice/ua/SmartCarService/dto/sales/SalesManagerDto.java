package com.smartcarservice.ua.SmartCarService.dto.sales;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import lombok.*;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesManagerDto {
    private String email;
    private String password;
    private String fullName;
    private String userName;
    private String dealerUsername;
}
