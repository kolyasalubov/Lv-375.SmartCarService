package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.AvatarDto;
import ua.ita.smartcarservice.entity.AvatarEntity;
import ua.ita.smartcarservice.repository.AvatarRepository;
import ua.ita.smartcarservice.service.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Override
    public void addAvatarToUser(AvatarDto avatarDto) {
        avatarRepository.save(dtoToEntity(avatarDto));
    }

    public AvatarEntity dtoToEntity(AvatarDto avatarDto) {
        return new AvatarEntity(avatarDto.getUserId(), avatarDto.getFileUrl(), avatarDto.getFilePath());
    }

    public AvatarDto entityToDto(AvatarEntity avatarEntity) {
        return new AvatarDto(avatarEntity.getUserId(),avatarEntity.getFileUrl(),avatarEntity.getFilePath());
    }

    @Override
    public void getAvatarDtoByUserId(Long userId) {
        avatarRepository.getByUserId(userId);
    }
}
