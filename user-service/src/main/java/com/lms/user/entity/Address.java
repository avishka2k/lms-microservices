package com.lms.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;

    @OneToOne(mappedBy = "address")
    private Student student;

    @OneToOne(mappedBy = "address")
    private Lecturer lecturer;
}
