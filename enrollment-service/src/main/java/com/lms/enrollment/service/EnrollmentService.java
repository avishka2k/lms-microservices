package com.lms.enrollment.service;

import com.lms.enrollment.client.StudentServiceClient;
import com.lms.enrollment.dto.StudentResponse;
import com.lms.enrollment.entity.StudentEnrollment;
import com.lms.enrollment.exception.ConflictException;
import com.lms.enrollment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public StudentEnrollment enrollStudent(StudentEnrollment studentEnrollment) {
        if (enrollmentRepository.existsByStudentId(studentEnrollment.getStudentId())) {
            throw new ConflictException("Student already enrolled in this course");
        }
        return enrollmentRepository.save(studentEnrollment);
    }
}
