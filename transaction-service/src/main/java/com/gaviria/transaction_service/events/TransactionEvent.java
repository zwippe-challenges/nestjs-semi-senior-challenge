package com.gaviria.transaction_service.events;

import java.util.UUID;

import com.gaviria.transaction_service.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private UUID transactionId;
    private TransactionType transactionType;
    private Double transactionAmount;
    private String transactionEmail;
}
