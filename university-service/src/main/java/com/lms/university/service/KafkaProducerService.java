package com.lms.university.service;

import com.lms.university.event.DepartmentEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService {

    private final NewTopic topic;
    private final KafkaTemplate<String, DepartmentEvent> kafkaTemplate;

    public void sendCourseEvent(DepartmentEvent departmentEvent) {
        Message<DepartmentEvent> message = MessageBuilder
                .withPayload(departmentEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }

}