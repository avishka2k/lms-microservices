package com.lms.announcement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Column(nullable = false)
    private String type;

    // Assignment specific fields
    private String assignmentCourseCode;
    private LocalDate assignmentDueDate;
    private String assignmentInstructions;
    private String assignmentInstructor;
    private LocalDate assignmentDate;

    // Exam specific fields
    private String examCourseCode;
    private LocalDate examDate;
    private String examTime;
    private String examLocation;
    private String examInstructor;
    private String examResources;

    // Event specific fields
    private LocalDate eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventOrganizer;
    private String eventContact;
    private String eventFlyer;
    private String eventRegistration;

    // Maintenance specific fields
    private LocalDateTime maintenanceStart;
    private LocalDateTime maintenanceEnd;
    private String maintenanceServices;
    private String maintenanceContact;
}

