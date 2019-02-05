package ua.ita.smartcarservice.entity.sales;


import ua.ita.smartcarservice.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sales_manager")
public class SalesManager extends UserBaseEntity {

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private Dealer dealer;

	public SalesManager(String email, String password, String fullName, String userName, Dealer dealer) {
		super(email, password, fullName, userName);
		this.dealer = dealer;
	}
    
    
}
