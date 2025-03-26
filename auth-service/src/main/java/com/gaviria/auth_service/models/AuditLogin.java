package com.gaviria.auth_service.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "audit_login" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogin {

    @Id
    @UuidGenerator
    private UUID id;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;
}
