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
public class StudentRequestDto implements UserRequestDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phoneNumber;
    @ElementCollection
    private AddressDto address;
    private String dateOfBirth;
    private String gender;
    private String joiningDate;

    private String nicImage;
    private String studentImage;
    private String birthCertificateImage;

    private int studentId;
    private int enrollmentNumber;
    private String intake;

    private String guardianName;
    private String guardianPhoneNumber;
    private String guardianEmail;
    private String relationship;
}
