package com.lms.user.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerRequestDto {
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String gender;
    private String profilePicture;
    private String joiningDate;

//    @ElementCollection
//    private List<String> coursesTaught;
//
//    @ElementCollection
//    private List<String> publications;
//
//    private String officeHours;
}