package ua.ita.smartcarservice.dto.files;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;

@Data
public class CarImageDTO {

    private Car carId;
    private String username;
    private String filePath;
    private String FileName;

    public CarImageDTO(Car carId, String username, String filePath, String fileName) {
        this.carId = carId;
        this.username = username;
        this.filePath = filePath;
        FileName = fileName;
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
