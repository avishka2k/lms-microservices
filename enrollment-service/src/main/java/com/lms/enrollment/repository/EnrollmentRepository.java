package com.lms.enrollment.repository;

import com.lms.enrollment.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface EnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    boolean existsByStudentId(Long studentId);
    StudentEnrollment findByStudentId(Long studentId);
}
