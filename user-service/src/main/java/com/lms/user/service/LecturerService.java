package com.lms.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

import java.util.Map;

@Service
public class LecturerService {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;


    public Map<String, Object> getCurrentUser(OidcUser user) {
        return user.getAttributes();
    }
}
