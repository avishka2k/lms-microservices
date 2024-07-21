package com.lms.user.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecturers")
public class Lecturer extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_personal_id")
    private LecturerPersonal lecturerPersonal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_academic_id")
    private LecturerAcademic lecturerAcademic;
}
