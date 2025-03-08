package com.gaviria.auth_service.scheduler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gaviria.auth_service.events.LoginEvent;
import com.gaviria.auth_service.kafka.KafkaProducerService;
import com.gaviria.auth_service.services.AuditLoginService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginSimulator {
    private final KafkaProducerService kafkaProducerService;
    private final AuditLoginService auditLoginService;

    @Scheduled(fixedRate = 8000)
    public void loginSimulator() {
        
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setUserId(UUID.randomUUID());
        loginEvent.setIpAddress(getIp());
        loginEvent.setTimeLogin(LocalDateTime.now().toString());
        auditLoginService.createAuditLogin(loginEvent);
        kafkaProducerService.sendLoginEvent(loginEvent);
    }

    public String getIp() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }
}
