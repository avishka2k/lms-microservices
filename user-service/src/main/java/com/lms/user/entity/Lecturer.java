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
    private String fullName;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;
    private String dateOfBirth;
    private String gender;
    private LocalDateTime joiningDate;
    private String nicImage;
    private String profileImage;

    private Long lecturerId;
    private String designation;
    private String department;
    private String faculty;
    private String officeLocation;
    private String workType;
    private String nic;
    private String highestDegree;
    private String institution;
    private String major;
    private String researchInterest;
    private String linkedIn;
    private String cv;
    private String emergencyPhone;

    @PrePersist
    protected void onCreate() {
        this.joiningDate = LocalDateTime.now();
    }
}
