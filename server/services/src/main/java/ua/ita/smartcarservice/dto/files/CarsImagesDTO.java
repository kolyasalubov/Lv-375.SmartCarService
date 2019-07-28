package ua.ita.smartcarservice.dto.files;

import lombok.Data;
import ua.ita.smartcarservice.dto.CarDto;

import java.util.List;


@Data
public class CarsImagesDTO {

    private CarDto carDto;
    private List<String> imageToDisplay;

    public CarsImagesDTO(CarDto carDto, List<String> imageToDisplay) {
        this.carDto = carDto;
        this.imageToDisplay = imageToDisplay;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public List<String> getImageToDisplay() {
        return imageToDisplay;
    }

    public void setImageToDisplay(List<String> imageToDisplay) {
        this.imageToDisplay = imageToDisplay;
    }
}
