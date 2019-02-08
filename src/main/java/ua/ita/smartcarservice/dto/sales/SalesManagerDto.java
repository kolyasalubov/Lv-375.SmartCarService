package ua.ita.smartcarservice.dto.sales;

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
