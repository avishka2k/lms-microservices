package com.lms.applicants.service;

import com.lms.applicants.entity.StudentApplicants;

import java.util.List;

public interface StudentApplicantsService {
    List<StudentApplicants> listStudentApplicants();
    StudentApplicants getStudentApplicantsById(Long id);
    StudentApplicants createStudentApplicants(StudentApplicants student);
    String deleteStudentApplicants(Long id);
}
