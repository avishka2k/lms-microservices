package com.lms.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fId;

    private String name;

    private String description;

    private String phone;

    private String email;

    private Long deanId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty", orphanRemoval = true)
    private Set<Department> departments = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        int randomNum = (int)(Math.random() * 9000) + 1000;
        this.fId = "FA" + randomNum;
        this.createdDate = LocalDateTime.now();
    }

}
