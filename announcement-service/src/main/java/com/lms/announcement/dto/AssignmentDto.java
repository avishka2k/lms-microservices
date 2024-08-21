package com.lms.announcement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssignmentDto extends AnnouncementDto {
    private String assignmentCourseCode;
    private LocalDate assignmentDueDate;
    private String assignmentInstructions;
    private String assignmentInstructor;
    private LocalDate assignmentDate;
}
