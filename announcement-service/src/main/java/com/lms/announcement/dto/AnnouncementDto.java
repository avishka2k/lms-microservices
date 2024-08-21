package com.lms.announcement.dto;

import lombok.Data;

@Data
public abstract class AnnouncementDto {
    private String title;
    private String description;
    private String type;
}
