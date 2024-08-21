package com.lms.announcement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExamDto extends AnnouncementDto {
    private String examCourseCode;
    private LocalDate examDate;
    private String examTime;
    private String examLocation;
    private String examInstructor;
    private String examResources;
}