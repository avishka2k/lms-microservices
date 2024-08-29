package com.lms.course.service;

import com.lms.course.entity.Course;
import com.lms.course.event.DepartmentEvent;
import com.lms.course.exception.NotFoundException;
import com.lms.course.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private CourseRepository courseRepository;

    @KafkaListener(topics = "university-topic", groupId = "course-group")
    public void listen(DepartmentEvent message) {

        if ("COURSE_ASSIGNED".equals(message.getEventType())) {
            Course course = courseRepository.findById(message.getCourseId()).orElse(null);

            if (course == null) {
                throw new NotFoundException("Course not found");
            }

            course.setDepartmentId(message.getDepartmentId());
            courseRepository.save(course);

        } else if ("COURSE_UNASSIGNED".equals(message.getEventType())) {
            Course course = courseRepository.findById(message.getCourseId()).orElse(null);

            if (course == null) {
                throw new NotFoundException("Course not found");
            }

            course.setDepartmentId(null);
            courseRepository.save(course);
        }
    }
}
