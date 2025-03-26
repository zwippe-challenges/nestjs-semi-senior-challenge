package com.gaviria.transaction_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.enums.TransactionType;
import com.gaviria.transaction_service.events.TransactionEvent;
import com.gaviria.transaction_service.mappers.TransactionMapper;
import com.gaviria.transaction_service.models.Transaction;
import com.gaviria.transaction_service.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testCreateTransactionFromEvent() {

        TransactionEvent transactionEvent = new TransactionEvent(UUID.randomUUID(), TransactionType.DEBIT, 100.0, "cristian@gaviria.org");
        Transaction transaction = new Transaction(UUID.randomUUID(), 100.0, "cristian@gaviria.org", TransactionType.DEBIT);

        when(transactionMapper.eventToEntity(transactionEvent)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.createTransactionFromEvent(transactionEvent);

        assertNotNull(result);
        assertEquals(transaction.getTransactionId(), result.getTransactionId());
        assertEquals(transaction.getTransactionAmount(), result.getTransactionAmount());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testCreateTransaction() {

        TransactionRequest request = new TransactionRequest(100.0, "cristian@gaviria.org", TransactionType.DEBIT);
        Transaction transaction = new Transaction(UUID.randomUUID(), 100.0, "cristian@gaviria.org",TransactionType.DEBIT);
        TransactionResponse response = new TransactionResponse(transaction.getTransactionId(), transaction.getTransactionAmount(), transaction.getTransactionType());

        when(transactionMapper.toEntity(request)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(transactionMapper.toResponse(transaction)).thenReturn(response);

        TransactionResponse result = transactionService.createTransaction(request);

        assertNotNull(result);
        assertEquals(response.getTransactionAmount(), result.getTransactionAmount());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testGetAllTransactions() {

        Pageable pageable = PageRequest.of(0, 10);
        List<Transaction> transactions = List.of(
                new Transaction(UUID.randomUUID(), 100.0, "cristian@gaviria.org", TransactionType.DEBIT),
                new Transaction(UUID.randomUUID(), 200.0, "cristian@gaviria.org", TransactionType.DEPOSIT)
        );
        Page<Transaction> transactionPage = new PageImpl<>(transactions, pageable, transactions.size());

        when(transactionRepository.findAll(pageable)).thenReturn(transactionPage);
        when(transactionMapper.toResponse(any())).thenAnswer(invocation -> {
            Transaction t = invocation.getArgument(0);
            return new TransactionResponse(t.getTransactionId(), t.getTransactionAmount(), t.getTransactionType());
        });

        Page<TransactionResponse> result = transactionService.getAllTransactions(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        verify(transactionRepository, times(1)).findAll(pageable);
    }
}
