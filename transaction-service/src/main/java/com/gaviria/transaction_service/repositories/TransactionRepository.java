package com.gaviria.transaction_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaviria.transaction_service.models.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, UUID> {
    
}
