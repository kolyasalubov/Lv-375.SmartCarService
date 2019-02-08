package ua.ita.smartcarservice.entity.sales;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.UserBaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales_manager")
public class SalesManager extends UserBaseEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;

    public SalesManager(String email, String password, String fullName, String userName, Dealer dealer) {
        this.dealer = dealer;
        this.setEmail(email);
        this.setPassword(password);
        this.setFullName(fullName);
        this.setUserName(userName);
    }
}