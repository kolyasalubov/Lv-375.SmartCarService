package ua.ita.smartcarservice.entity.sales;


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





    @OneToMany(fetch = FetchType.LAZY,mappedBy = "dealer")
    private Set<TechnicalService>technicalServices;


    @OneToMany(mappedBy = "dealer")
   private Set<Car> cars;


    @OneToMany(mappedBy = "dealer")
    private Set<SalesManager> salesManagers;


}
