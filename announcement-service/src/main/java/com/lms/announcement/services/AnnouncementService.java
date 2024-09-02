package com.lms.announcement.services;

import com.lms.announcement.dto.*;
import com.lms.announcement.entity.Announcement;
import com.lms.announcement.exception.InvalidDateException;
import com.lms.announcement.exception.NotFoundException;
import com.lms.announcement.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private AnnouncementRepository announcementRepository;

    public Announcement createAssignment(AssignmentDto assignmentDto) {
        Announcement assignment = createCommonAnnouncement(assignmentDto);
        assignment.setAssignmentCourseCode(assignmentDto.getAssignmentCourseCode());
        assignment.setAssignmentDueDate(assignmentDto.getAssignmentDueDate());
        assignment.setAssignmentInstructions(assignmentDto.getAssignmentInstructions());
        assignment.setAssignmentInstructor(assignmentDto.getAssignmentInstructor());
        if (assignmentDto.getAssignmentDueDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Assignment due date cannot be in the past");
        }
        if (assignmentDto.getAssignmentDueDate() == null) {
            throw new InvalidDateException("Assignment due cannot be null");
        }
        return announcementRepository.save(assignment);
    }

    public Announcement createEvent(EventDto eventDto) {
        Announcement event = createCommonAnnouncement(eventDto);
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setEventLocation(eventDto.getEventLocation());
        event.setEventOrganizer(eventDto.getEventOrganizer());
        event.setEventContact(eventDto.getEventContact());
        event.setEventFlyer(eventDto.getEventFlyer());
        event.setEventRegistration(eventDto.getEventRegistration());
        event.setEventDate(LocalDate.now());
        if (eventDto.getEventDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Event date cannot be in the past");
        }
        if (eventDto.getEventDate() == null || eventDto.getEventTime() == null) {
            throw new InvalidDateException("Event date or time cannot be null");
        }
        return announcementRepository.save(event);
    }

    public Announcement createExam(ExamDto examDto) {
        Announcement exam = createCommonAnnouncement(examDto);
        exam.setExamCourseCode(examDto.getExamCourseCode());
        exam.setExamDate(examDto.getExamDate());
        exam.setExamTime(examDto.getExamTime());
        exam.setExamLocation(examDto.getExamLocation());
        exam.setExamInstructor(examDto.getExamInstructor());
        exam.setExamResources(examDto.getExamResources());
        exam.setExamDate(LocalDate.now());
        if (examDto.getExamDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Exam date cannot be in the past");
        }
        if (examDto.getExamDate() == null || examDto.getExamTime() == null) {
            throw new InvalidDateException("Exam date or time cannot be null");
        }
        return announcementRepository.save(exam);
    }

    public Announcement createMaintenance(MaintenanceDto maintenanceDto) {
        Announcement maintenance = createCommonAnnouncement(maintenanceDto);
        maintenance.setMaintenanceStart(maintenanceDto.getMaintenanceStart());
        maintenance.setMaintenanceEnd(maintenanceDto.getMaintenanceEnd());
        maintenance.setMaintenanceServices(maintenanceDto.getMaintenanceServices());
        maintenance.setMaintenanceContact(maintenanceDto.getMaintenanceContact());
        if (maintenanceDto.getMaintenanceStart().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Maintenance start date cannot be in the past");
        }
        if (maintenanceDto.getMaintenanceEnd().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Maintenance end date cannot be in the past");
        }
        if (maintenanceDto.getMaintenanceStart().isAfter(maintenanceDto.getMaintenanceEnd())) {
            throw new InvalidDateException("Maintenance start date cannot be after maintenance end date");
        }
        return announcementRepository.save(maintenance);
    }

    public List<AnnouncementResponseDto> listAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream().map(this::convertToAnnouncementResponseDto).toList();
    }

    public List<AssignmentDto> listAssignments() {
        List<Announcement> assignments = announcementRepository.findAllByType("assignment");
        return assignments.stream().map(this::convertToAssignmentDto).toList();
    }

    public List<EventDto> listEvents() {
        List<Announcement> events = announcementRepository.findAllByType("event");
        return events.stream().map(this::convertToEventDto).toList();
    }

    public List<ExamDto> listExams() {
        List<Announcement> exams = announcementRepository.findAllByType("exam");
        return exams.stream().map(this::convertToExamDto).toList();
    }

    public List<MaintenanceDto> listMaintenances() {
        List<Announcement> maintenances = announcementRepository.findAllByType("maintenance");
        return maintenances.stream().map(this::convertToMaintenanceDto).toList();
    }

    private AnnouncementResponseDto convertToAnnouncementResponseDto(Announcement announcement) {
        AnnouncementResponseDto dto = new AnnouncementResponseDto();
        dto.setId(announcement.getId());
        dto.setTitle(announcement.getTitle());
        dto.setType(announcement.getType());
        dto.setDescription(announcement.getDescription());
        return dto;
    }

    private Announcement createCommonAnnouncement(AnnouncementDto dto) {
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setType(dto.getType());
        announcement.setDescription(dto.getDescription());
        return announcement;
    }

    private AssignmentDto convertToAssignmentDto(Announcement announcement) {
        AssignmentDto dto = new AssignmentDto();
        dto.setTitle(announcement.getTitle());
        dto.setType(announcement.getType());
        dto.setDescription(announcement.getDescription());
        dto.setAssignmentCourseCode(announcement.getAssignmentCourseCode());
        dto.setAssignmentDueDate(announcement.getAssignmentDueDate());
        dto.setAssignmentInstructions(announcement.getAssignmentInstructions());
        dto.setAssignmentInstructor(announcement.getAssignmentInstructor());
        return dto;
    }

    private EventDto convertToEventDto(Announcement announcement) {
        EventDto dto = new EventDto();
        dto.setTitle(announcement.getTitle());
        dto.setType(announcement.getType());
        dto.setDescription(announcement.getDescription());
        dto.setEventDate(announcement.getEventDate());
        dto.setEventTime(announcement.getEventTime());
        dto.setEventLocation(announcement.getEventLocation());
        dto.setEventOrganizer(announcement.getEventOrganizer());
        dto.setEventContact(announcement.getEventContact());
        dto.setEventFlyer(announcement.getEventFlyer());
        dto.setEventRegistration(announcement.getEventRegistration());
        return dto;
    }

    private MaintenanceDto convertToMaintenanceDto(Announcement announcement) {
        MaintenanceDto dto = new MaintenanceDto();
        dto.setTitle(announcement.getTitle());
        dto.setType(announcement.getType());
        dto.setDescription(announcement.getDescription());
        dto.setMaintenanceStart(announcement.getMaintenanceStart());
        dto.setMaintenanceEnd(announcement.getMaintenanceEnd());
        dto.setMaintenanceServices(announcement.getMaintenanceServices());
        dto.setMaintenanceContact(announcement.getMaintenanceContact());
        return dto;
    }

    private ExamDto convertToExamDto(Announcement announcement) {
        ExamDto dto = new ExamDto();
        dto.setTitle(announcement.getTitle());
        dto.setType(announcement.getType());
        dto.setDescription(announcement.getDescription());
        dto.setExamCourseCode(announcement.getExamCourseCode());
        dto.setExamDate(announcement.getExamDate());
        dto.setExamTime(announcement.getExamTime());
        dto.setExamLocation(announcement.getExamLocation());
        dto.setExamInstructor(announcement.getExamInstructor());
        dto.setExamResources(announcement.getExamResources());
        return dto;
    }

    public String deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElse(null);
        if (announcement == null) {
            throw new NotFoundException("Announcement not found");
        }
        announcementRepository.delete(announcement);
        return "Announcement deleted successfully";
    }
}
