package ua.ita.smartcarservice.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {

    Path getFileStorageLocation();

    List<String> findAvatarByUsername(String username);

    String saveAvatar(String username, MultipartFile file);

    List<String> findCarImagesByUsername(String username);

    List<String> findCarImageByCarId(Long id);

    String saveCarImage(String username, MultipartFile file);

}
