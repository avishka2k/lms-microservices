package com.lms.announcement.controller;

import com.lms.announcement.dto.AssignmentDto;
import com.lms.announcement.dto.EventDto;
import com.lms.announcement.dto.ExamDto;
import com.lms.announcement.dto.MaintenanceDto;
import com.lms.announcement.exception.InvalidDateException;
import com.lms.announcement.exception.NotFoundException;
import com.lms.announcement.services.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling announcements related to assignments, events, exams, and maintenance.
 */
@RestController
@RequestMapping("/api/announcement")
@AllArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    /**
     * Retrieves all announcements.
     *
     * @return ResponseEntity containing the list of all announcements or an error message.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAnnouncements() {
        try {
            return ResponseEntity.ok(announcementService.listAllAnnouncements());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get announcements");
        }
    }

    /**
     * Creates a new assignment announcement.
     *
     * @param assignmentDto Data Transfer Object (DTO) containing assignment details.
     * @return ResponseEntity with the created assignment or an error message.
     */
    @PostMapping("/assignment")
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDto assignmentDto) {
        try {
            return ResponseEntity.ok(announcementService.createAssignment(assignmentDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    /**
     * Creates a new event announcement.
     *
     * @param eventDto DTO containing event details.
     * @return ResponseEntity with the created event or an error message.
     */
    @PostMapping("/event")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {
        try {
            return ResponseEntity.ok(announcementService.createEvent(eventDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    /**
     * Creates a new exam announcement.
     *
     * @param examDto DTO containing exam details.
     * @return ResponseEntity with the created exam or an error message.
     */
    @PostMapping("/exam")
    public ResponseEntity<?> createExam(@RequestBody ExamDto examDto) {
        try {
            return ResponseEntity.ok(announcementService.createExam(examDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    /**
     * Creates a new maintenance announcement.
     *
     * @param maintenanceDto DTO containing maintenance details.
     * @return ResponseEntity with the created maintenance announcement or an error message.
     */
    @PostMapping("/maintenance")
    public ResponseEntity<?> createMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        try {
            return ResponseEntity.ok(announcementService.createMaintenance(maintenanceDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    /**
     * Deletes an announcement by its ID.
     *
     * @param id The ID of the announcement to delete.
     * @return ResponseEntity indicating success or failure of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(announcementService.deleteAnnouncement(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete announcement");
        }
    }
}
