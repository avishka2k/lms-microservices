package com.lms.enrollment.dto;

import lombok.Data;

/**
 * DTO for student information.
 * Used to transfer student details between different parts of the application.
 */
@Data
public class StudentResponse {
    private String id;
    private String name;
    private String email;
}
