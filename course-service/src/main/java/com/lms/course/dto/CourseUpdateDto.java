package com.lms.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDto {
    private String title;
    private String description;
    private String lecturer;
    private Long duration; // Duration in hours
    private String level;
    private String language;
    private String format; // Can be 'online', 'in-person', or 'hybrid'
    private int credits;
}