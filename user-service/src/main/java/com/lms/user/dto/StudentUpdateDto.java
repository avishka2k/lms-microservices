package com.lms.user.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDto {
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    @ElementCollection
    private AddressDto address;
    private String dateOfBirth;
    private String gender;

    private String nicImage;
    private String studentImage;
    private String birthCertificateImage;

    private Long studentId;
    private String enrollmentNumber;
    private String intake;

    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    private String guardianRelationship;
}