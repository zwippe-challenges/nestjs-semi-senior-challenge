package com.gaviria.transaction_service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.events.TransactionEvent;
import com.gaviria.transaction_service.mappers.TransactionMapper;
import com.gaviria.transaction_service.models.Transaction;
import com.gaviria.transaction_service.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    
    public Transaction createTransactionFromEvent(TransactionEvent transaction) {
        return transactionRepository.save(transactionMapper.eventToEntity(transaction));
    }

    public TransactionResponse createTransaction(TransactionRequest transaction) {
        return transactionMapper.toResponse(transactionRepository.save(transactionMapper.toEntity(transaction)));
    }

    public Page<TransactionResponse> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable).map(transactionMapper::toResponse);
    }
}
