package com.lms.user.controller;

import com.lms.user.dto.CognitoUserDto;
import com.lms.user.dto.UserRequestDto;
import com.lms.user.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Get Admin", description = "Returns the details of the logged in admin")
    @GetMapping()
    public Map<String, Object> getUserDetails(@AuthenticationPrincipal OidcUser user) {
        return adminService.getCurrentUser(user);
    }

    @Operation(summary = "Get Students", description = "Returns a list of all students")
    @GetMapping("/getStudents")
    public List<CognitoUserDto> getStudents() {
        String group = "STUDENT";
        return adminService.listUsersInGroup(group);
    }

    @Operation(summary = "Get Lecturers", description = "Returns a list of all lecturers")
    @GetMapping("/getLecturers")
    public List<CognitoUserDto> getLecturers() {
        String group = "LECTURER";
        return adminService.listUsersInGroup(group);
    }

    @Operation(summary = "Get User by username", description = "Returns a user details by username")
    @GetMapping("/getUser/{username}")
    public Map<String, String> getUser(@PathVariable String username) {
        return adminService.getUserByUsername(username);
    }

    @Operation(summary = "Create Student", description = "Creates a new student")
    @PostMapping("/createStudent")
    public String createStudent(@RequestBody UserRequestDto dto) {
        return adminService.createUser(dto, "STUDENT");
    }

    @Operation(summary = "Create Lecturer", description = "Creates a new lecturer")
    @PostMapping("/createLecturer")
    public String createLecturer(@RequestBody UserRequestDto dto) {
        return adminService.createUser(dto, "LECTURER");
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
