package com.lms.user.controller;

import com.lms.user.entity.Student;
import com.lms.user.exception.NotFoundException;
import com.lms.user.service.AdminService;
import com.lms.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/student")
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;
    private final AdminService adminService;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserInfo() {
        return userService.getUserInfo();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{username}/username")
    public ResponseEntity<Student> getStudentByUsername(@PathVariable String username) {
        try {
            Student student = adminService.getStudentByUsername(username);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Student not found");
        }
    }
}
