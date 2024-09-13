package com.lms.enrollment.repository;

import com.lms.enrollment.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Repository interface for managing {@link StudentEnrollment} entities.
 */
@RepositoryRestController
public interface EnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

    /**
     * Checks if an enrollment exists for the given student ID.
     */
    boolean existsByStudentId(Long studentId);
    /**
     * Retrieves an enrollment by the given student ID.
     */
    StudentEnrollment findByStudentId(Long studentId);
}
