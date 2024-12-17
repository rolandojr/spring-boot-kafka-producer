package org.example.producer.kafkaproducer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.producer.kafkaproducer.config.ApplicationProperties;
import org.example.producer.kafkaproducer.dto.Employee;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaAvroProducer {

    private ApplicationProperties properties;
    private KafkaTemplate<String, Employee> kafkaTemplate;

    public void send(Employee employee) {

        Message<Employee> message = MessageBuilder
                .withPayload(employee)
                .setHeader(KafkaHeaders.TOPIC, properties.getTopic())
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .build();

        kafkaTemplate.send(message)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        ex.printStackTrace();
                    }
                    log.info("Employee sent successfully to topic {} with partition {} with offset {}", result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                });
    }
}
