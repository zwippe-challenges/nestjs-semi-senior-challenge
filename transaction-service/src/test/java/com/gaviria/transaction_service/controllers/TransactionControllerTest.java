package com.gaviria.transaction_service.controllers;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaviria.transaction_service.dto.TransactionRequest;
import com.gaviria.transaction_service.dto.TransactionResponse;
import com.gaviria.transaction_service.enums.TransactionType;
import com.gaviria.transaction_service.services.TransactionService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Mock
    private PageableHandlerMethodArgumentResolver pageableResolver;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void testCreateTransaction() throws Exception {
        TransactionResponse transaction = new TransactionResponse();
        transaction.setTransactionAmount(Math.random() * 1000);
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setTransactionType(TransactionType.DEBIT);

        when(transactionService.createTransaction(any(TransactionRequest.class))).thenReturn(transaction);

        String jsonRequest = objectMapper.writeValueAsString(transaction);

        mockMvc.perform(post("/api/transactions")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", is(transaction.getTransactionId().toString())))
                .andExpect(jsonPath("$.transactionAmount", is(transaction.getTransactionAmount())))
                .andExpect(jsonPath("$.transactionType", is(transaction.getTransactionType().toString())));
    }

    @Test
    void testGetAllTransactions() throws Exception {
        List<TransactionResponse> transactions = List.of(new TransactionResponse(), new TransactionResponse());
        Pageable pageable = PageRequest.of(0, 10);
        Page<TransactionResponse> page = new PageImpl<>(transactions, pageable, transactions.size());

        when(transactionService.getAllTransactions(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/transactions")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "transactionId,asc")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
