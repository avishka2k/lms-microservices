package com.lms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String gender;
    private String profilePicture;
    private String joiningDate;
}
