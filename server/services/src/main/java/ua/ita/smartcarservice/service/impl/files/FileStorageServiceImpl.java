package ua.ita.smartcarservice.service.impl.files;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.dto.files.AvatarDto;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.files.AvatarService;
import ua.ita.smartcarservice.service.files.HashService;
import ua.ita.smartcarservice.service.config.FileStorageConfig;
import ua.ita.smartcarservice.service.files.FileStorageService;
import ua.ita.smartcarservice.exceptions.FileStorageException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private HashService hashService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private UserService userService;

    @Autowired
    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Path getFileStorageLocation() {
        return fileStorageLocation;
    }

    /* find user avatar*/
    @Override
    public List<String> findAvatarByUsername(String username) {
        List<String> avatarEncoded = new ArrayList<>();
        if (avatarService.findByUsername(username) == null) {
            return avatarEncoded;
        }

        Path avatarLocation = Paths.get(avatarService.findByUsername(username).getFilePath());
        File avatarFile = new File(avatarService.findByUsername(username).getFilePath());
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

    /* add or change avatar for user*/
    @Override
    public String saveAvatar(String username, MultipartFile file) {
        String fileHash = hashService.avatarHash(userService.findByUserName(username).getId(),
                username,
                userService.findByUserName(username).getEmail());

        String fileName = StringUtils.cleanPath(fileHash + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            avatarService.addAvatarToUser(new AvatarDto(
                    userService.findByUserName(username), username, targetLocation.toString(), fileName));

            byte[] avatarBytes = IOUtils.toByteArray(file.getInputStream());
            String encodeBase64 = Base64.getEncoder().encodeToString(avatarBytes);
            String avatarToDisplay = "data:image/" + FilenameUtils.getExtension(file.getOriginalFilename()) + ";base64," + encodeBase64;
            return avatarToDisplay;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public List<String> findCarImagesByUsername(String username) {
        return null;
    }

    @Override
    public List<String> findCarImageByCarId(Long id) {
        return null;
    }

    @Override
    public String saveCarImage(String username, MultipartFile file) {
        return null;
    }
}

