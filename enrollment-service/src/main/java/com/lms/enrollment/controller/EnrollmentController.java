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

    // read enrollment
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getEnrollment(@PathVariable Long studentId) {
        StudentEnrollment enrollment = enrollmentService.getEnrollment(studentId);
        if (enrollment == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    // update enrollment
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateEnrollment(@PathVariable Long studentId, @RequestParam Long newCourseId) {
        StudentEnrollment enrollment = enrollmentService.updateEnrollment(studentId, newCourseId);
        if (enrollment == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    // reject student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> rejectStudent(@PathVariable Long studentId) {
        try {
            enrollmentService.rejectStudent(studentId);
            return new ResponseEntity<>("Student rejected", HttpStatus.OK);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to reject student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
