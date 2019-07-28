package ua.ita.smartcarservice.entity.files;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Entity
@Data
@Table(name = "car_norms")
public class CarNorms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = false)
    private String brand;

    @Column(length = 20, nullable = false, unique = false)
    private String model;

    @Column(length = 4, nullable = false, unique = false)
    @JsonFormat(pattern = "yyyy")
    private String graduation_year;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double norm_of_tire_pressure;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_brake_fluid;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_oil;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_coolant;

    public CarNorms() {
    }

    public CarNorms(String brand, String model, String graduation_year, Double norm_of_tire_pressure, Double volume_of_brake_fluid, Double volume_of_oil, Double volume_of_coolant) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.norm_of_tire_pressure = norm_of_tire_pressure;
        this.volume_of_brake_fluid = volume_of_brake_fluid;
        this.volume_of_oil = volume_of_oil;
        this.volume_of_coolant = volume_of_coolant;
    }

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

    public Double getNorm_of_tire_pressure() {
        return norm_of_tire_pressure;
    }

    public void setNorm_of_tire_pressure(Double norm_of_tire_pressure) {
        this.norm_of_tire_pressure = norm_of_tire_pressure;
    }

    public Double getVolume_of_brake_fluid() {
        return volume_of_brake_fluid;
    }

    public void setVolume_of_brake_fluid(Double volume_of_brake_fluid) {
        this.volume_of_brake_fluid = volume_of_brake_fluid;
    }

    public Double getVolume_of_oil() {
        return volume_of_oil;
    }

    public void setVolume_of_oil(Double volume_of_oil) {
        this.volume_of_oil = volume_of_oil;
    }

    public Double getVolume_of_coolant() {
        return volume_of_coolant;
    }

    public void setVolume_of_coolant(Double volume_of_coolant) {
        this.volume_of_coolant = volume_of_coolant;
    }
}
