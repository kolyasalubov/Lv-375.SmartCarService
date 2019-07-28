package ua.ita.smartcarservice.controller.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.service.files.AvatarService;
import ua.ita.smartcarservice.service.files.CarImageService;
import ua.ita.smartcarservice.service.files.HashService;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.files.FileStorageService;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private CarImageService carImageService;

    @Autowired
    private HashService hashService;

    @Autowired
    private UserService userService;

    @PostMapping("/uploadAvatar/{username}")
    public ResponseEntity<String> uploadAvatar(@PathVariable String username, MultipartFile file) {
        return new ResponseEntity<>(fileStorageService.saveAvatar(username, file), HttpStatus.OK);
    }

    @GetMapping("/downloadAvatar/{username}")
    public ResponseEntity<List<String>> downloadAvatar(@PathVariable String username) {
        return new ResponseEntity<>(fileStorageService.findAvatarByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/upload/car/image/{carId}")
    public ResponseEntity uploadCarImage(@PathVariable Long carId, MultipartFile file) {
        carImageService.addImageToCar(carId, file);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/download/car/image/{carId}")
    public ResponseEntity<List<String>> downloadCarImage(@PathVariable Long carId) {
        return new ResponseEntity<>(carImageService.findByCarId(carId), HttpStatus.OK);
    }

    @GetMapping("/download/car/image/username/{username}")
    public ResponseEntity<Map<Long, String>> downloadCarsImages(@PathVariable String username) {
        return new ResponseEntity<>(carImageService.findByUsername(username), HttpStatus.OK);
    }

}