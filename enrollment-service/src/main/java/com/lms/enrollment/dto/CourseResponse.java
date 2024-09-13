package com.lms.enrollment.dto;

import lombok.Data;

/**
 * DTO for course information.
 * Used to transfer course details between different parts of the application.
 */
@Data
public class CourseResponse {

    private Long id;
    private String name;
}
