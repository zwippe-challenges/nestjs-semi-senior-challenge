package com.gaviria.transaction_service.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.gaviria.transaction_service.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    
    @Id
    @UuidGenerator
    private UUID transactionId;

    @Column( name = "transactionAmount", nullable = false)
    private Double transactionAmount;

    @Column( name = "transactionEmail", nullable = false)
    private String transactionEmail;
    
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
