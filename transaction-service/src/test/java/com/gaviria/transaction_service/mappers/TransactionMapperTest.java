package com.gaviria.transaction_service.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.enums.TransactionType;
import com.gaviria.transaction_service.events.TransactionEvent;
import com.gaviria.transaction_service.models.Transaction;

public class TransactionMapperTest {
    private TransactionMapper transactionMapper;

    @BeforeEach
    void setUp() {
        transactionMapper = new TransactionMapper();
    }

    @Test
    void testEventToEntity() {

        TransactionEvent transactionEvent = new TransactionEvent(UUID.randomUUID(), TransactionType.DEBIT, 100.0, "cristian@gaviria.org");

        Transaction transaction = transactionMapper.eventToEntity(transactionEvent);

        assertNotNull(transaction);
        assertEquals(transactionEvent.getTransactionAmount(), transaction.getTransactionAmount());
        assertEquals(transactionEvent.getTransactionEmail(), transaction.getTransactionEmail());
        assertEquals(transactionEvent.getTransactionType(), transaction.getTransactionType());
    }

    @Test
    void testToEntity() {

        TransactionRequest transactionRequest = new TransactionRequest(100.0, "cristian@gaviria.org", TransactionType.DEBIT);

        Transaction transaction = transactionMapper.toEntity(transactionRequest);

        assertNotNull(transaction);
        assertEquals(transactionRequest.getTransactionAmount(), transaction.getTransactionAmount());
        assertEquals(transactionRequest.getTransactionEmail(), transaction.getTransactionEmail());
        assertEquals(transactionRequest.getTransactionType(), transaction.getTransactionType());
    }

    @Test
    void testToResponse() {

        UUID transactionId = UUID.randomUUID();
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setTransactionAmount(300.0);
        transaction.setTransactionType(TransactionType.DEPOSIT);

        TransactionResponse response = transactionMapper.toResponse(transaction);

        assertNotNull(response);
        assertEquals(transaction.getTransactionId(), response.getTransactionId());
        assertEquals(transaction.getTransactionAmount(), response.getTransactionAmount());
        assertEquals(transaction.getTransactionType(), response.getTransactionType());
    }
}
