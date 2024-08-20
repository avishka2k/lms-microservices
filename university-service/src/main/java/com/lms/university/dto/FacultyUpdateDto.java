package com.lms.university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyUpdateDto {
    private String name;
    private String description;
    private String phone;
    private String email;
    private Long deanId;
}
