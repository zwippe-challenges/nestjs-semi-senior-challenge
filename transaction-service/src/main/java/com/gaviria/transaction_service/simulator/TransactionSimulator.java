package com.gaviria.transaction_service.simulator;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gaviria.transaction_service.enums.TransactionType;
import com.gaviria.transaction_service.events.TransactionEvent;
import com.gaviria.transaction_service.kafka.KafkaProducerService;
import com.gaviria.transaction_service.models.Transaction;
import com.gaviria.transaction_service.services.TransactionService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransactionSimulator {

    private final KafkaProducerService kafkaProducerService;

    private final TransactionService transactionService;

    @Scheduled(fixedRate = 5000)
    public void simulateTransaction() {
        TransactionEvent eventSimulated = new TransactionEvent();
        eventSimulated.setTransactionAmount(Math.random() * 1000);
        eventSimulated.setTransactionEmail("cristian@gaviria.org");
        eventSimulated
                .setTransactionType(TransactionType.values()[new Random().nextInt(TransactionType.values().length)]);
        Transaction transaction = transactionService.createTransactionFromEvent(eventSimulated);
        eventSimulated.setTransactionId(transaction.getTransactionId());
        kafkaProducerService.sendTransactionEvent(eventSimulated);
    }
}
