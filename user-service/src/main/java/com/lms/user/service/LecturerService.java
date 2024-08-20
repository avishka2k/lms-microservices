package com.lms.user.service;

import com.lms.user.entity.Lecturer;
import com.lms.user.repository.LecturerRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LecturerService {

    private LecturerRepository lecturerRepository;

    public Map<String, Object> getCurrentUser(OidcUser user) {
        return user.getAttributes();
    }
}
