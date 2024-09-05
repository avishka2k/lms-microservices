package com.lms.user.controller;

import com.lms.user.entity.Lecturer;
import com.lms.user.exception.NotFoundException;
import com.lms.user.service.AdminService;
import com.lms.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user/lecturer")
public class LecturerController {

    private final UserService userService;
    private final AdminService adminService;

    @PreAuthorize("hasRole('LECTURER')")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserInfo() {
        return userService.getUserInfo();
    }

    @PreAuthorize("hasRole('LECTURER')")
    @GetMapping("/{username}/username")
    public ResponseEntity<Lecturer> getLecturerByUsername(@PathVariable String username) {
        try {
            Lecturer lecturer = adminService.getLecturerByUsername(username);
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        } catch (Exception e) {
            throw new NotFoundException("Lecturer not found");
        }
    }

}
