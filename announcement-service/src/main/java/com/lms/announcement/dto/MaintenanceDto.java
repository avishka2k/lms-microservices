package com.lms.announcement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MaintenanceDto extends AnnouncementDto {
    private LocalDateTime maintenanceStart;
    private LocalDateTime maintenanceEnd;
    private String maintenanceServices;
    private String maintenanceContact;
}
