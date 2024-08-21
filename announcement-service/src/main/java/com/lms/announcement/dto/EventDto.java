package com.lms.announcement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto extends AnnouncementDto {
    private LocalDate eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventOrganizer;
    private String eventContact;
    private String eventFlyer;
    private String eventRegistration;
}
