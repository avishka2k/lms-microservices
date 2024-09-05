package com.lms.user.controller;

import com.lms.user.config.ConfigProperties;
import com.lms.user.dto.*;
import com.lms.user.entity.Lecturer;
import com.lms.user.entity.Student;
import com.lms.user.exception.ConflictException;
import com.lms.user.exception.NotFoundException;
import com.lms.user.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    private final ConfigProperties configProperties;

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody StudentRequestDto dto) {
        try {
            String groupName = configProperties.getStudent();
            String result = adminService.createStudent(dto, groupName);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ConflictException("Failed to create student: " + e.getMessage());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = adminService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Student not found with id: " + id);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = adminService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to retrieve students: " + e.getMessage());
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDto dto) {
        try {
            String result = adminService.updateStudent(id, dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to update student with id: " + id);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            String result = adminService.deleteStudent(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to delete student");
        }
    }

    @PostMapping("/lecturers")
    public ResponseEntity<String> createLecturer(@RequestBody LecturerRequestDto dto) {
        try {
            String groupName = configProperties.getLecturer();
            String result = adminService.createLecturer(dto, groupName);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ConflictException("Failed to create lecturer: " + e.getMessage());
        }
    }

    @GetMapping("/lecturers/{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long id) {
        try {
            Lecturer lecturer = adminService.getLecturerById(id);
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Lecturer not found with id: " + id);
        }
    }

    @GetMapping("/lecturers")
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        try {
            List<Lecturer> lecturers = adminService.getAllLecturers();
            return new ResponseEntity<>(lecturers, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to retrieve lecturers: " + e.getMessage());
        }
    }

    @PutMapping("/lecturers/{id}")
    public ResponseEntity<String> updateLecturer(@PathVariable Long id, @RequestBody LecturerUpdateDto dto) {
        try {
            String result = adminService.updateLecturer(id, dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to update lecturer with id: " + id);
        }
    }

    @DeleteMapping("/lecturers/{id}")
    public ResponseEntity<String> deleteLecturer(@PathVariable Long id) {
        try {
            String result = adminService.deleteLecturer(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to delete lecturer");
        }
    }

    @GetMapping("/users/group")
    public ResponseEntity<List<CognitoUserDto>> listUsersInGroup(@RequestParam String groupName) {
        try {
            List<CognitoUserDto> users = adminService.listUsersInGroup(groupName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            throw new ConflictException("Failed to retrieve users in group: " + groupName);
        }
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<Map<String, String>> getUserByUsername(@PathVariable String username) {
        try {
            Map<String, String> user = adminService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("User not found with username: " + username);
        }
    }

}

