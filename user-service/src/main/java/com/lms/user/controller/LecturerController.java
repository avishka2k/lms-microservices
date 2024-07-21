package com.lms.user.controller;

import com.lms.user.service.LecturerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/lecturer")
public class LecturerController {

    private final LecturerService lecturerService;

    @PreAuthorize("hasRole('LECTURER')")
    @Operation(summary = "Get Lecturer", description = "Returns the details of the logged in lecturer")
    @GetMapping()
    public Map<String, Object> getUserDetails(@AuthenticationPrincipal OidcUser user) {
        return lecturerService.getCurrentUser(user);
    }

}
