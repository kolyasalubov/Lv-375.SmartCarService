package ua.ita.smartcarservice.entity.files;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;

import javax.persistence.*;

@Entity
@Data
@Table(name = "car_image")
public class CarImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "carId")
    private Car carId;
    @Column
    private String username;
    @Column
    private String filePath;
    @Column
    private String FileName;

    public CarImageEntity() {
    }

    public CarImageEntity(Car carId, String username, String filePath, String fileName) {
        this.carId = carId;
        this.username = username;
        this.filePath = filePath;
        FileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}

