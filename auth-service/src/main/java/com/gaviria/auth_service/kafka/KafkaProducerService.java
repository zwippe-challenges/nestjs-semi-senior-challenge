package com.gaviria.auth_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gaviria.auth_service.events.LoginEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaProducerService {
    
    private final KafkaTemplate<String, LoginEvent> kafkaTemplate;
    private final String topic = "login-events";

    public void sendLoginEvent(LoginEvent loginEvent) {
        kafkaTemplate.send(topic, loginEvent);
        System.out.println("Sent login event" + loginEvent);
    }
}
