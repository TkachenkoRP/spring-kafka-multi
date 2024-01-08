package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSenderService {
    @Value("${app.kafka.kafkaOrderTopic}")
    private String topicName;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void send(OrderEvent message) {
        kafkaTemplate.send(topicName, message);
    }
}
