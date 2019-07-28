package ua.ita.smartcarservice.service.impl.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.files.AvatarDto;
import ua.ita.smartcarservice.entity.files.AvatarEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.files.AvatarRepository;
import ua.ita.smartcarservice.service.files.AvatarService;
import ua.ita.smartcarservice.service.UserService;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AvatarDto findByUsername(String username) {
        AvatarEntity avatarEntity = avatarRepository.findByUsername(username);
        if (avatarEntity == null) {
            return null;
        } else {
            return entityToDto(avatarEntity);
        }
    }

    @Override
    public void deleteByUsername(String username) {
        avatarRepository.deleteByUsername(username);
    }

    @Override
    public void addAvatarToUser(AvatarDto avatarDto) {
        AvatarEntity avatarEntity = avatarRepository.findByUsername(avatarDto.getUsername());
        if (avatarEntity == null) {
            avatarRepository.save(dtoToEntity(avatarDto));
        } else {
            avatarEntity.setFilePath(avatarDto.getFilePath());
            avatarEntity.setFileName(avatarDto.getFileName());
            avatarRepository.save(avatarEntity);
        }
    }

    public AvatarEntity dtoToEntity(AvatarDto avatarDto) {
        return new AvatarEntity(
                userRepository.getByUsername(avatarDto.getUsername()),
                avatarDto.getUsername(),
                avatarDto.getFilePath(),
                avatarDto.getFileName());
    }

    public AvatarDto entityToDto(AvatarEntity avatarEntity) {
        return new AvatarDto(
                userRepository.getByUsername(avatarEntity.getUsername()),
                avatarEntity.getUsername(),
                avatarEntity.getFilePath(),
                avatarEntity.getFileName());
    }
}
