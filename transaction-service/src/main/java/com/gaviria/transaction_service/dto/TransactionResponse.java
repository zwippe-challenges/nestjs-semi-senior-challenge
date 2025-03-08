package com.gaviria.transaction_service.dto;

import java.util.UUID;

import com.gaviria.transaction_service.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionResponse {
    private UUID transactionId;
    private Double transactionAmount;
    private TransactionType transactionType;
}
