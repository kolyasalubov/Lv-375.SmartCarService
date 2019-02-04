package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDto {

    private LocalDateTime startSession;

    private LocalDateTime endSession;

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }
}
