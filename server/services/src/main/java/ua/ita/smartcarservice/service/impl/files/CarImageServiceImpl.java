package ua.ita.smartcarservice.service.impl.files;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.dto.files.CarImageDTO;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.files.CarImageEntity;
import ua.ita.smartcarservice.exceptions.FileStorageException;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.files.CarImageRepository;
import ua.ita.smartcarservice.service.config.FileStorageConfig;
import ua.ita.smartcarservice.service.files.CarImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class CarImageServiceImpl implements CarImageService {

    private final Path fileStorageLocation;

    @Autowired
    private CarImageRepository carImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    public CarImageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public void addImageToCar(Long carId, MultipartFile file) {
        Car car = carRepository.getCarById(carId);
        CarImageEntity carImage = carImageRepository.findByCarId(car);

        String fileHash = DigestUtils.md5Hex(carId.toString() +
                car.getModel() +
                car.getBrand());

        String fileName = StringUtils.cleanPath(fileHash + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

        if (carImage == null) {
            carImageRepository.save(dtoToEntity(new CarImageDTO(
                    carRepository.getCarById(carId), carRepository.getCarById(carId).getUser().getUsername(), targetLocation.toString(), fileName)));

        } else {
            carImage.setFilePath(targetLocation.toString());
            carImage.setFileName(fileName);
            carImageRepository.save(carImage);
        }
    }

    @Override
    public List<String> findByCarId(Long id) {
        List<String> avatarEncoded = new ArrayList<>();
        Car car = carRepository.getCarById(id);

        if (carImageRepository.findByCarId(car) == null) {
            return avatarEncoded;
        }

        Path avatarLocation = Paths.get(carImageRepository.findByCarId(car).getFilePath());
        File avatarFile = new File(carImageRepository.findByCarId(car).getFilePath());
        String fileExtension = FilenameUtils.getExtension(avatarFile.getName());

        try {
            byte[] avatarBytes = Files.readAllBytes(avatarLocation);
            String encodeBase64 = Base64.getEncoder().encodeToString(avatarBytes);
            String avatarToDisplay = "data:image/" + fileExtension + ";base64," + encodeBase64;
            avatarEncoded.add(avatarToDisplay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avatarEncoded;
    }

    @Override
    public Map<Long, String> findByUsername(String username) {
        Map<Long, String> imageMap = new HashMap<>();
        List<CarImageEntity> imageList = carImageRepository.findByUsername(username);
        for (CarImageEntity i : imageList) {
            Path pathToImage = Paths.get(i.getFilePath());
            File imageFile = new File(i.getFilePath());
            String imageExtension = FilenameUtils.getExtension(imageFile.getName());
            try {
                byte[] imageBytes = Files.readAllBytes(pathToImage);
                String encodeBase64 = Base64.getEncoder().encodeToString(imageBytes);
                String imageToDisplay = "data:image/" + imageExtension + ";base64," + encodeBase64;
                imageMap.put(i.getCarId().getId(), imageToDisplay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageMap;
    }

    @Override
    public void deleteCarImageByCarId(Long id) {
        carImageRepository.deleteById(id);
    }


    public CarImageEntity dtoToEntity(CarImageDTO imageDto) {
        return new CarImageEntity(
                imageDto.getCarId(),
                imageDto.getUsername(),
                imageDto.getFilePath(),
                imageDto.getFileName());
    }

    public CarImageDTO entityToDto(CarImageEntity imageEntity) {
        return new CarImageDTO(
                imageEntity.getCarId(),
                imageEntity.getUsername(),
                imageEntity.getFilePath(),
                imageEntity.getFileName());
    }
}
