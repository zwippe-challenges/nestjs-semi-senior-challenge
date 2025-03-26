package com.gaviria.transaction_service.mappers;

import org.springframework.stereotype.Component;

import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.events.TransactionEvent;
import com.gaviria.transaction_service.models.Transaction;

@Component
public class TransactionMapper {
    public Transaction eventToEntity(TransactionEvent transactionEvent) {
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(transactionEvent.getTransactionAmount());
        transaction.setTransactionEmail(transactionEvent.getTransactionEmail());
        transaction.setTransactionType(transactionEvent.getTransactionType());
        return transaction;
    }

    public Transaction toEntity(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(transactionRequest.getTransactionAmount());
        transaction.setTransactionEmail(transactionRequest.getTransactionEmail());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        return transaction;
    }

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(transaction.getTransactionId(), transaction.getTransactionAmount(),
                transaction.getTransactionType());
    }
}
