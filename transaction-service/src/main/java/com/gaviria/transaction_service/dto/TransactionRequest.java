package com.gaviria.transaction_service.dto;

import com.gaviria.transaction_service.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Double transactionAmount;
    private String transactionEmail;
    private TransactionType transactionType;
}
