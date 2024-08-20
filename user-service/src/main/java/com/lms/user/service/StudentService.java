package com.lms.user.service;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {

    public Map<String, Object> getCurrentUser(OidcUser user) {
        return user.getAttributes();
    }
}
