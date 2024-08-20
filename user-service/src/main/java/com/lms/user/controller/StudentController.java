package com.lms.user.controller;

import com.lms.user.service.StudentService;
import com.lms.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/student")
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;

    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get Student", description = "Returns the details of the logged in student")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserInfo() {
        return userService.getUserInfo();
    }
}
