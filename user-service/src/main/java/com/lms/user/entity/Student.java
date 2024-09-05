package com.lms.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;
    private String dateOfBirth;
    @Column(nullable = false)
    private String gender;
    private LocalDateTime joiningDate;

    private String nicImage;
    private String studentImage;
    private String birthCertificateImage;

    private Long studentId;
    private String enrollmentNumber;
    private String intake;
    private Long courseId;

    private String guardianName;
    private String guardianPhone;
    private String guardianRelationship;
    private String guardianEmail;

    @PrePersist
    protected void onCreate() {
        this.joiningDate = LocalDateTime.now();
    }
}