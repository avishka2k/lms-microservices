package com.lms.university.controller;

import com.lms.university.entity.Department;
import com.lms.university.exception.NotFoundException;
import com.lms.university.service.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public/uni")
public class PublicViewController {

    private final FacultyService facultyService;

    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> departments = facultyService.getDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
