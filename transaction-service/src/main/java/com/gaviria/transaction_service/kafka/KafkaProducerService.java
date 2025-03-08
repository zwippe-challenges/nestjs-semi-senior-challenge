package com.gaviria.transaction_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gaviria.transaction_service.events.TransactionEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;
    private final String topic = "transaction-events";

    public void sendTransactionEvent(TransactionEvent transactionEvent) {
        kafkaTemplate.send(topic, transactionEvent);
        System.out.println("Event sent" + transactionEvent);
    }
}
