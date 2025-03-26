package com.gaviria.auth_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaviria.auth_service.models.AuditLogin;

public interface AuditLoginRepository extends JpaRepository<AuditLogin, UUID>{
    
}
