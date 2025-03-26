package com.gaviria.auth_service.services;

import org.springframework.stereotype.Service;

import com.gaviria.auth_service.events.LoginEvent;
import com.gaviria.auth_service.models.AuditLogin;
import com.gaviria.auth_service.repositories.AuditLoginRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuditLoginService {
    private AuditLoginRepository auditLoginRepository;

    public AuditLogin createAuditLogin(LoginEvent loginEvent) {
        AuditLogin auditLogin = new AuditLogin();
        auditLogin.setIpAddress(loginEvent.getIpAddress());
        auditLogin.setUserId(loginEvent.getUserId());
        return auditLoginRepository.save(auditLogin);
    }
}
