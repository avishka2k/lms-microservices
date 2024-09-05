package com.lms.user.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lms.user.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerRequestDto implements UserRequestDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    @ElementCollection
    private AddressDto address;
    private String dateOfBirth;
    private String gender;
    private LocalDateTime joiningDate;
    private String nicImage;
    private String profileImage;

    private Long lecturerId;
    private String designation;
    private String department;
    private String faculty;
    private String officeLocation;
    private String workType;
    private String nic;
    private String highestDegree;
    private String institution;
    private String major;
    private String researchInterest;
    private String linkedIn;
    private String cv;
    private String emergencyPhone;
}