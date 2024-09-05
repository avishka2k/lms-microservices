package com.lms.user.service.impl;

import com.lms.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.security.oauth2.client.provider.cognito.user-info-uri}")
    private String userInfoUri;

    public Map<String, Object> getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accessToken;
        if (principal instanceof OidcUser user) {
            accessToken = user.getIdToken().getTokenValue();
        } else if (principal instanceof Jwt jwt) {
            accessToken = jwt.getTokenValue();
        } else {
            System.out.println("Unknown principal type: " + principal.getClass());
            return Collections.emptyMap();
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }
}
