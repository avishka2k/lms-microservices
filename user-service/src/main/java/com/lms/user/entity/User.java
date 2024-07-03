package com.lms.user.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
//    private String department;
//    private String organization;
//    private String phone;
//    private String address;
//    private String status;
//    private String createdBy;
//    private String updatedBy;
//    private String createdDate;
//    private String updatedDate;
//    @Enumerated(EnumType.STRING)
//    private UserRole role;
}

