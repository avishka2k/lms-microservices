package com.lms.university.controller;

import com.lms.university.event.DepartmentEvent;
import com.lms.university.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaController {

    private final KafkaProducerService producerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody DepartmentEvent message) {
        producerService.sendCourseEvent(message);
        return ResponseEntity.ok("Message published to Kafka topic");
    }
}
