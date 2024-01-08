package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Order;
import org.example.model.OrderEvent;
import org.example.service.KafkaSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class OrderController {

    private final KafkaSenderService kafkaSenderService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order message) {
        kafkaSenderService.send(OrderEvent.fromOrder(message));
        return ResponseEntity.ok("Message send to kafka");
    }
}
