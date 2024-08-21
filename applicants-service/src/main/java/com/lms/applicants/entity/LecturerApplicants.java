package com.lms.applicants.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicant_lecturer")
public class LecturerApplicants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String department;
    private String highestQualification;
    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
}
