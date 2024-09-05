package com.lms.user.dto;

import com.lms.user.entity.Address;
import jakarta.persistence.*;
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
    private String phone;
    @ElementCollection
    private AddressDto address;
    private String dateOfBirth;
    private String gender;
    private String joiningDate;

    private String nicImage;
    private String studentImage;
    private String birthCertificateImage;

    private Long studentId;
    private String enrollmentNumber;
    private String intake;
    private Long courseId;

    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    private String guardianRelationship;
}