package ua.ita.smartcarservice.entity.sales;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.UserBaseEntity;
import lombok.Data;
import ua.ita.smartcarservice.entity.car.Car;

import javax.persistence.*;
import java.util.Set;

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

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "salesManager")
	private Set<TradeIn> tradeIns;

}
