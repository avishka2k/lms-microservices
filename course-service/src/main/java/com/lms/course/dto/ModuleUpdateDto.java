package com.lms.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleUpdateDto {
    private String title;
    private String description;
    private Long duration; // Duration in hours
    private String level;
    private String language;
    private Long credits;
    private String semester;
}