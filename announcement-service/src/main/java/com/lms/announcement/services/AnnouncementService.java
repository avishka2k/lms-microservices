package com.lms.announcement.services;

import com.lms.announcement.dto.*;
import com.lms.announcement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    Announcement createAssignment(AssignmentDto assignmentDto);
    Announcement createEvent(EventDto eventDto);
    Announcement createExam(ExamDto examDto);
    Announcement createMaintenance(MaintenanceDto maintenanceDto);

    List<AnnouncementResponseDto> listAllAnnouncements();
    List<AssignmentDto> listAssignments();
    List<EventDto> listEvents();
    List<ExamDto> listExams();
    List<MaintenanceDto> listMaintenances();
    String deleteAnnouncement(Long id);
}
