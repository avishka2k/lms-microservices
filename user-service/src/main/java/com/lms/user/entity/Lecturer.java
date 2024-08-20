package com.lms.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String fullName;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;
    private String dateOfBirth;
    @Column(nullable = false)
    private String gender;
    private String joiningDate;
    private String nicImage;
    private String profileImage;

    @Column(nullable = false)
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
