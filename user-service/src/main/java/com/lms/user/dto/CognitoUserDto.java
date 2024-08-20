package com.lms.user.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CognitoUserDto {
    private String username;
    private String email;
    private String name;
    private Map<String, String> attributes;
}
