package com.gaviria.transaction_service.kafka;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.gaviria.transaction_service.enums.TransactionType;
import com.gaviria.transaction_service.events.TransactionEvent;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    private KafkaProducerService kafkaProducerService;

    @BeforeEach
    void setUp() {
        kafkaProducerService = new KafkaProducerService(kafkaTemplate);
    }

    @Test
    void testSendTransactionEvent() {
        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(UUID.randomUUID());
        event.setTransactionAmount(100.50);
        event.setTransactionType(TransactionType.DEBIT);

        kafkaProducerService.sendTransactionEvent(event);

        verify(kafkaTemplate, times(1)).send("transaction-events", event);
    }
}
