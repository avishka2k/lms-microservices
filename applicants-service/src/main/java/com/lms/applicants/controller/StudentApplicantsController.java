package com.lms.applicants.controller;

import com.lms.applicants.entity.StudentApplicants;
import com.lms.applicants.exception.ConflictException;
import com.lms.applicants.exception.NotFoundException;
import com.lms.applicants.service.StudentApplicantsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/applicants/student")
public class StudentApplicantsController {

    private final StudentApplicantsService studentApplicantsService;

    // List all student applicants
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listStudents() {
        try {
            List<StudentApplicants> students = studentApplicantsService.listStudentApplicants();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to list student applicants", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get student applicants by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        try {
            StudentApplicants student = studentApplicantsService.getStudentApplicantsById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get student applicants", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create student applicants
    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody StudentApplicants student) {
        try {
            StudentApplicants newStudent = studentApplicantsService.createStudentApplicants(student);
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create student applicants", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete student applicants
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") Long id) {
        try {
            String message = studentApplicantsService.deleteStudentApplicants(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete student applicants", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
