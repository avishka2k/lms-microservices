package com.lms.applicants.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentApplicantsDto {
    private  String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;
    @ElementCollection
    private AddressDto address;
    private String zipCode;
    private String email;
    private String phone;
    private Long enrollmentNumber;
    private String course;
    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    private Date dateOfApplication;
}
