package ua.ita.smartcarservice.entity.sales;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ua.ita.smartcarservice.entity.UserBaseEntity;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "dealer")
public class Dealer extends UserBaseEntity implements Serializable {


    @Column(length = 15, nullable = false, unique = true)
    private String phone_number;


    @Column(length = 100, nullable = false, unique = false)
    private String address;


    @Column(length = 100, nullable = false, unique = true)
    private String edr;

    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
    private Set<TechnicalService>technicalServices;

    @JsonIgnore
//    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
   private Set<Car> cars;

    @JsonManagedReference
    @OneToMany(mappedBy = "dealer")
    private Set<SalesManager> salesManagers;

    public Dealer(){};

    public Dealer(String email, String password, String fullName, String userName, String phone_number, String address, String edr, Set<TechnicalService> technicalServices, Set<Car> cars, Set<SalesManager> salesManagers) {
        super(email, password, fullName, userName);
        this.phone_number = phone_number;
        this.address = address;
        this.edr = edr;
        this.technicalServices = technicalServices;
        this.cars = cars;
        this.salesManagers = salesManagers;
    }
    public Dealer(String email, String password, String fullName, String userName, String phone_number, String address, String edr) {
        super(email, password, fullName, userName);
        this.phone_number = phone_number;
        this.address = address;
        this.edr = edr;

    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdr() {
        return edr;
    }

    public void setEdr(String edr) {
        this.edr = edr;
    }

    public Set<TechnicalService> getTechnicalServices() {
        return technicalServices;
    }

    public void setTechnicalServices(Set<TechnicalService> technicalServices) {
        this.technicalServices = technicalServices;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<SalesManager> getSalesManagers() {
        return salesManagers;
    }

    public void setSalesManagers(Set<SalesManager> salesManagers) {
        this.salesManagers = salesManagers;
    }
}


