package com.lms.announcement.dto;

import lombok.Data;

@Data
public class AnnouncementResponseDto {
    private Long id;
    private String title;
    private String description;
    private String type;
}
