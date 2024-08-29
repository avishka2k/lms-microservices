package com.lms.course.service;

import com.lms.course.entity.Course;
import com.lms.course.event.DepartmentEvent;
import com.lms.course.exception.NotFoundException;
import com.lms.course.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic", groupId = "university-group")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
