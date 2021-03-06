package ua.ita.smartcarservice.entity.files;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_avatar")
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;
    @Column
    private String username;
    @Column
    private String filePath;
    @Column
    private String fileName;


    public AvatarEntity() {
    }

    public AvatarEntity(UserEntity userId, String username, String filePath, String fileName) {
        this.userId = userId;
        this.username = username;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
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
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
