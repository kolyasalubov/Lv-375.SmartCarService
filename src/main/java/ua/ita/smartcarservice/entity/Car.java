package ua.ita.smartcarservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.entity.sensors.alert.VehicleInspection;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@Table(name = "cars")
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
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private DealerDetails dealer;

    //TODO delete CascadeType.ALL after token implementation
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = true)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private Set<VehicleInspection> vehicleInspections;

    @JsonIgnore
    @OneToMany (mappedBy = "car")
    private Set<WorkTime> workTimes;
/*
    @JsonManagedReference
    @OneToMany (mappedBy = "car")
   private Set<Session> sessions;
*/

    public Car() {
    }

    //For new cars
    public Car(String brand, String model, String graduation_year, String number, Double price, String vin,
               Date end_guarantee, DealerDetails dealer, UserEntity user, Set<VehicleInspection> vehicleInspections) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.end_guarantee = end_guarantee;
        this.dealer = dealer;
        this.user = user;
        this.vehicleInspections = vehicleInspections;
    }

    //For used cars
    public Car(String brand, String model, String graduation_year, String number, String vin, UserEntity user) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.user = user;
    }




}