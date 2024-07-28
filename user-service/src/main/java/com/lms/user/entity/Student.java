package com.lms.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {
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
