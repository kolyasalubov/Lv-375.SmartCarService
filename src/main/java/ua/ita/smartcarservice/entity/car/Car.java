package ua.ita.smartcarservice.entity.car;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sensors.alert.VehicleInspection;
import ua.ita.smartcarservice.entity.sto.Session;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = false)
    private String brand;

    @Column(length = 20, nullable = false, unique = false)
    private String model;


    @Column(length = 20, nullable = false, unique = false)
    private String graduation_year;


    @Column(length = 100, nullable = false, unique = true)
    private String number;

    @Column(nullable = true, unique = false)
    private Double price;


    @Column(nullable = false, unique = true)
    private String vin;

    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_guarantee;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id", nullable = true)
    private Dealer dealer;

    //TODO delete CascadeType.ALL after token implementation
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = true)
    private CarOwner carOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private Set<VehicleInspection> vehicleInspections;

    public Car() {
    }

    //For new cars
    public Car(String brand, String model, String graduation_year, String number, Double price, String vin,
               Date end_guarantee, Dealer dealer, CarOwner carOwner, Set<VehicleInspection> vehicleInspections) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.end_guarantee = end_guarantee;
        this.dealer = dealer;
        this.carOwner = carOwner;
        this.vehicleInspections = vehicleInspections;
    }

    //For used cars
    public Car(String brand, String model, String graduation_year, String number, String vin, CarOwner carOwner) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.carOwner = carOwner;
    }

    public Car(String brand, String model, String graduation_year, String number, String vin, Dealer dealer,Double price) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.dealer = dealer;
        this.price=price;
    }
	@JsonManagedReference
	@OneToMany (mappedBy = "car")
    private Set<Session> sessions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year = graduation_year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getEnd_guarantee() {
        return end_guarantee;
    }

    public void setEnd_guarantee(Date end_guarantee) {
        this.end_guarantee = end_guarantee;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    public Set<VehicleInspection> getVehicleInspections() {
        return vehicleInspections;
    }

    public void setVehicleInspections(Set<VehicleInspection> vehicleInspections) {
        this.vehicleInspections = vehicleInspections;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", graduation_year='" + graduation_year + '\'' +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", vin='" + vin + '\'' +
                ", end_guarantee=" + end_guarantee +
                ", dealer=" + dealer +
                ", carOwner=" + carOwner +
                '}';
    }
}