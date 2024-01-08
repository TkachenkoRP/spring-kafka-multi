package org.example.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderEvent;
import org.example.model.OrderStatusEvent;
import org.example.service.KafkaSenderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {
    private final KafkaSenderService kafkaSenderService;

    @KafkaListener(topics = "${app.kafka.kafkaOrderTopic}",
            groupId = "${app.kafka.kafkaOrderStatusGroupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);

        OrderStatusEvent orderStatusEvent = new OrderStatusEvent();
        orderStatusEvent.setStatus("CREATED");
        orderStatusEvent.setDate(Instant.now());

        kafkaSenderService.send(orderStatusEvent);
    }
}