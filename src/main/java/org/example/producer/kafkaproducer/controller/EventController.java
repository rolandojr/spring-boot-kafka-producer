package org.example.producer.kafkaproducer.controller;

import lombok.AllArgsConstructor;
import org.example.producer.kafkaproducer.dto.Employee;
import org.example.producer.kafkaproducer.service.KafkaAvroProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EventController {
    private KafkaAvroProducer producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee) {
        producer.send(employee);
        return "message publised !!";
    }
}
