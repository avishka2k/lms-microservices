package com.lms.enrollment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity representing a student's enrollment in a course.
 * Maps to the "student_enrollments" table in the database.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_enrollments")
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long studentId;
    @Column(nullable = false)
    private Long courseId;
    private String programOfStudy;
    @Column(nullable = false)
    private String enrollmentStatus;
    @Column(nullable = false)
    private LocalDate enrollmentDate;
    private String academicYear;
    private String semester;
    private Integer creditsEnrolled;
    private String enrollmentType;
    private String gradeLevel;
    private String enrollmentKey;
}
