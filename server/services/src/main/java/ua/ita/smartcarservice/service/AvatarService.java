package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.AvatarDto;
import ua.ita.smartcarservice.entity.AvatarEntity;

public interface AvatarService {
    void addAvatarToUser(AvatarDto avatarDto);
    void getAvatarDtoByUserId(Long userId);

}
