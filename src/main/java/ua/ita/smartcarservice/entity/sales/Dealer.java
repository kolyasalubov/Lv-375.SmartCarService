package ua.ita.smartcarservice.entity.sales;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import ua.ita.smartcarservice.entity.UserBaseEntity;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dealer")
public class Dealer extends UserBaseEntity {


    @Column(length = 15, nullable = false, unique = true)
    private String phone_number;


    @Column(length = 100, nullable = false, unique = false)
    private String address;


    @Column(length = 100, nullable = false, unique = true)
    private String edr;

    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
    private Set<TechnicalService>technicalServices;

    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
   private Set<Car> cars;

    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
    private Set<SalesManager> salesManagers;

    public Dealer(String email, String password, String fullName, String userName, String phone_number, String address, String edr, Set<TechnicalService> technicalServices, Set<Car> cars, Set<SalesManager> salesManagers) {
        super(email, password, fullName, userName);
        this.phone_number = phone_number;
        this.address = address;
        this.edr = edr;
        this.technicalServices = technicalServices;
        this.cars = cars;
        this.salesManagers = salesManagers;
    }
}


