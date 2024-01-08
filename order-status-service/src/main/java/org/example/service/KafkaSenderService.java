package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderStatusEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderService {
    @Value("${app.kafka.kafkaOrderStatusTopic}")
    private String topicName;
    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;

    public void send(OrderStatusEvent message) {
        kafkaTemplate.send(topicName, message);
        log.info("Message send to kafka");
    }
}
