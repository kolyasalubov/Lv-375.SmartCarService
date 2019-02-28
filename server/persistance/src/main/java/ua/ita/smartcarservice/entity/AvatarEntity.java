package ua.ita.smartcarservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "avatar")
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn
    @OneToOne()
    private Long userId;
    @JoinColumn
    private String fileUrl;
    @JoinColumn
    private String filePath;

}
