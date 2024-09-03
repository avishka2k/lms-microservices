package com.lms.applicants.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicant_student")
public class StudentApplicants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;
    private String email;
    private String phone;
    private Long enrollmentNumber;
    private String course;
    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    private String guardianRelationship;
    @Column(nullable = false)
    private LocalDateTime dateOfApplication;

    @PrePersist
    protected void onCreate() {
        this.dateOfApplication = LocalDateTime.now();
    }
}
