package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDto {

    private LocalDateTime startSession;

    private LocalDateTime endSession;

}
