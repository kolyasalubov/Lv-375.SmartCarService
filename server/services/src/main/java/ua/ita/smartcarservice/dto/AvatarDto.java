package ua.ita.smartcarservice.dto;

import lombok.Data;

@Data
public class AvatarDto {
    private Long userId;
    private String username;
    private String fileUrl;
    private String filePath;

    public AvatarDto(Long userId, String username, String fileUrl, String filePath) {
        this.userId = userId;
        this.username = username;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
    }
}
