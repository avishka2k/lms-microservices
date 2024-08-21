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

@RestController
@RequestMapping("/api/announcement")
@AllArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/all")
    public ResponseEntity<?> getAnnouncements() {
        try {
            return ResponseEntity.ok(announcementService.listAllAnnouncements());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get announcements");
        }
    }

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
