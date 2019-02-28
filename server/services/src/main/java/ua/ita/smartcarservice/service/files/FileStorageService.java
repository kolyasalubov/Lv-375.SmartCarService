package ua.ita.smartcarservice.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.service.impl.files.FileStorageConfig;

public interface FileStorageService {
    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);

}
