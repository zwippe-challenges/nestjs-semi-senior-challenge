package com.gaviria.auth_service.events;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginEvent {
    private UUID userId;
    private String timeLogin;
    private String ipAddress;
}
