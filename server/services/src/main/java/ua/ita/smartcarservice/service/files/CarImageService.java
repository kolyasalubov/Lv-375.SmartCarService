package ua.ita.smartcarservice.service.files;

import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.dto.files.CarImageDTO;
import ua.ita.smartcarservice.entity.files.CarImageEntity;

import java.util.List;
import java.util.Map;

public interface CarImageService {

    void addImageToCar(Long carId, MultipartFile file);

    List <String> findByCarId(Long id);

    Map<Long, String> findByUsername(String username);

    void deleteCarImageByCarId(Long id);

}
