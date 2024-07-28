package com.lms.user.dto;

import com.lms.user.entity.Address;
import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    @ElementCollection
    private Address address;
    private String dateOfBirth;
    private String gender;
    private String joiningDate;
    private String nicImage;
    private String profileImage;

    private int LecturerId;
    private String designation;
    private String department;
    private String faculty;
    private String officeLocation;
    private String employType;
    private String nic;
    private String highestDegreeObtained;
    private String highestDegreeInstitute;
    private String specialization;
    private String researchInterest;
    private String linkedIn;
    private String cv;
}