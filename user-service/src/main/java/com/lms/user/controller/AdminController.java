package com.lms.user.controller;

import com.lms.user.config.ConfigProperties;
import com.lms.user.dto.CognitoUserDto;
import com.lms.user.dto.LecturerRequestDto;
import com.lms.user.dto.StudentRequestDto;
import com.lms.user.service.AdminService;
import com.lms.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final ConfigProperties configProperties;

    @Operation(summary = "Get User Info", description = "Returns the user information from Cognito")
    @GetMapping("/userInfo")
    public Map<String, Object> getUserInfo() {
        return userService.getUserInfo();
    }

    @GetMapping("/getAdmins")
    public List<CognitoUserDto> getAdmins() {
        return adminService.listUsersInGroup("ADMIN");
    }

    @Operation(summary = "Get Students", description = "Returns a list of all students")
    @GetMapping("/getStudents")
    public List<CognitoUserDto> getStudents() {
        String group = configProperties.getStudent();
        return adminService.listUsersInGroup(group);
    }

    @Operation(summary = "Get Lecturers", description = "Returns a list of all lecturers")
    @GetMapping("/getLecturers")
    public List<CognitoUserDto> getLecturers() {
        String group = configProperties.getLecturer();
        return adminService.listUsersInGroup(group);
    }

    @Operation(summary = "Get User by username", description = "Returns a user details by username")
    @GetMapping("/getUser/{username}")
    public Map<String, String> getUser(@PathVariable String username) {
        return adminService.getUserByUsername(username);
    }

    @Operation(summary = "Create Student", description = "Creates a new student")
    @PostMapping("/createStudent")
    public String createStudent(@RequestBody StudentRequestDto dto) {
        return adminService.createStudent(dto, "STUDENT");
    }

    @Operation(summary = "Create Lecturer", description = "Creates a new lecturer")
    @PostMapping("/createLecturer")
    public String createLecturer(@RequestBody LecturerRequestDto dto) {
        return adminService.createLecturer(dto, "LECTURER");
    }

    @Operation(summary = "Update User Attributes", description = "Updates user attributes")
    @PutMapping("/updateUser/{username}")
    public ResponseEntity<String> updateUserAttributes(@PathVariable String username, @RequestBody Map<String, String> attributes) {
        String result = adminService.updateUserAttributes(username, attributes);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete User", description = "Deletes a user")
    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        return adminService.deleteUser(username);
    }
}
