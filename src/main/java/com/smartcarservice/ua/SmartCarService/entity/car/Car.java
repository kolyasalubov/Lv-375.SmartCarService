package com.smartcarservice.ua.SmartCarService.entity.car;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JoinColumn(name="dealer", nullable=false)
//    @ManyToMany
//    private Dealer dealer;

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
    private Long vin;

    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_guarantee;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private Dealer dealer;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private CarOwner carOwner;

	@OneToMany (mappedBy = "car")
	private List<VehicleInspection> vehicleInspections;
	
	//For new cars
		public Car(String brand, String model, String graduation_year, String number, Double price, Long vin,
			Date end_guarantee, Dealer dealer, CarOwner carOwner, List<VehicleInspection> vehicleInspections) {
		super();
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
	public Car(String brand, String model, String graduation_year, String number, Long vin, CarOwner carOwner) {
		super();
		this.brand = brand;
		this.model = model;
		this.graduation_year = graduation_year;
		this.number = number;
		this.vin = vin;
		this.carOwner = carOwner;
	}



	//Getters & Setters
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

	public Long getVin() {
		return vin;
	}

	public void setVin(Long vin) {
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

	public List<VehicleInspection> getVehicleInspections() {
		return vehicleInspections;
	}

	public void setVehicleInspections(List<VehicleInspection> vehicleInspections) {
		this.vehicleInspections = vehicleInspections;
	}
	
}