package com.lms.user.controller;

import com.lms.user.service.LecturerService;
import com.lms.user.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user/lecturer")
public class LecturerController {

    private final LecturerService lecturerService;
    private final UserService userService;

    @PreAuthorize("hasRole('LECTURER')")
    @Operation(summary = "Get Lecturer", description = "Returns the details of the logged in lecturer")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserInfo() {
        return userService.getUserInfo();
    }

}
