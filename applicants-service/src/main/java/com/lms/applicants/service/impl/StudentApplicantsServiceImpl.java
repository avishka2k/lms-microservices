package com.lms.applicants.service.impl;

import com.lms.applicants.entity.StudentApplicants;
import com.lms.applicants.exception.ConflictException;
import com.lms.applicants.exception.NotFoundException;
import com.lms.applicants.repository.StudentApplicantsRepository;
import com.lms.applicants.service.StudentApplicantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentApplicantsServiceImpl implements StudentApplicantsService {

    @Autowired
    private StudentApplicantsRepository studentApplicantsRepository;

    // List all student applicants
    public List<StudentApplicants> listStudentApplicants() {
        List<StudentApplicants> students = studentApplicantsRepository.findAll();
        if (students.isEmpty()) {
            throw new NotFoundException("No student applicants found");
        }
        return students;
    }

    // Get student applicants by id
    public StudentApplicants getStudentApplicantsById(Long id) {
        StudentApplicants student = studentApplicantsRepository.findById(id).orElse(null);
        if (student == null) {
            throw new NotFoundException("Student applicants not found");
        }
        return student;
    }

    // Create student applicants
    public StudentApplicants createStudentApplicants(StudentApplicants student) {
        if (studentApplicantsRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Your Application already exists");
        }
        return studentApplicantsRepository.save(student);
    }

    // Delete student applicants
    public String deleteStudentApplicants(Long id) {
        if (!studentApplicantsRepository.existsById(id)) {
            throw new NotFoundException("Student applicants not found");
        }
        studentApplicantsRepository.deleteById(id);
        return "Student applicants deleted successfully";
    }

}
