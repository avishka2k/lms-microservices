package com.lms.enrollment.controller;

import com.lms.enrollment.client.StudentServiceClient;
import com.lms.enrollment.dto.StudentResponse;
import com.lms.enrollment.entity.StudentEnrollment;
import com.lms.enrollment.exception.ConflictException;
import com.lms.enrollment.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    private final StudentServiceClient studentServiceClient;

    private final EnrollmentService enrollmentService;

    @GetMapping("/students")
    public List<StudentResponse> getStudents() {
        return studentServiceClient.getStudents();
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestBody StudentEnrollment studentEnrollment) {
        try {
            StudentEnrollment enrollment = enrollmentService.enrollStudent(studentEnrollment);
            return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to enroll student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
