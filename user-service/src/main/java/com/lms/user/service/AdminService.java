package com.lms.user.service;

import com.lms.user.dto.CognitoUserDto;
import com.lms.user.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

//    @Value("${aws.cognito.userPoolId}")
    private String userPoolId = "ap-southeast-1_fPd4etWzw";

    public Map<String, Object> getCurrentUser(OidcUser user) {
        if (user == null) {
            return Collections.emptyMap();
        }
        return user.getAttributes();
    }

    public String createUser(UserRequestDto dto, String groupName) {

        String username = dto.getUsername();
        String email = dto.getEmail();
        String name = dto.getName();
        String address = dto.getAddress();
//        String phoneNumber = dto.getPhoneNumber();
        String dateOfBirth = dto.getDateOfBirth();
        String profilePicture = dto.getProfilePicture();
        String gender = dto.getGender();

        try {

            AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .userAttributes(Arrays.asList(
                            AttributeType.builder().name("email").value(email).build(),
                            AttributeType.builder().name("email_verified").value("false").build(),
                            AttributeType.builder().name("name").value(name).build(),
                            AttributeType.builder().name("address").value(address).build(),
                            AttributeType.builder().name("gender").value(gender).build(),
                            AttributeType.builder().name("birthdate").value(dateOfBirth).build(),
//                            AttributeType.builder().name("phone_number").value(phoneNumber).build(),
//                            AttributeType.builder().name("phone_number_verified").value("true").build(),
                            AttributeType.builder().name("picture").value(profilePicture).build(),
                            AttributeType.builder().name("custom:join_date").value(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).build()
                    ))
                    .build();

            AdminCreateUserResponse createUserResponse = cognitoClient.adminCreateUser(createUserRequest);

            // Assign user to group
            AdminAddUserToGroupRequest addUserToGroupRequest = AdminAddUserToGroupRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .groupName(groupName)
                    .build();

            cognitoClient.adminAddUserToGroup(addUserToGroupRequest);

            return createUserResponse.user().username();
        } catch (UsernameExistsException e) {
            throw new RuntimeException("User already exists with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create user in Cognito");
        }
    }

    public Map<String, String> getUserByUsername(String username) {
        try {
            AdminGetUserRequest getUserRequest = AdminGetUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .build();
            AdminGetUserResponse getUserResponse = cognitoClient.adminGetUser(getUserRequest);

            return getUserResponse.userAttributes().stream()
                    .collect(Collectors.toMap(
                            AttributeType::name,
                            AttributeType::value
                    ));
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get user from Cognito");
        }
    }

    public List<CognitoUserDto> listUsersInGroup(String groupName) {
        try {
            ListUsersInGroupRequest request = ListUsersInGroupRequest.builder()
                    .userPoolId(userPoolId)
                    .groupName(groupName)
                    .build();
            ListUsersInGroupResponse response = cognitoClient.listUsersInGroup(request);
            return response.users().stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to list groups for user in Cognito");
        }
    }

    private CognitoUserDto convertToDto(UserType userType) {
        CognitoUserDto dto = new CognitoUserDto();
        dto.setUsername(userType.username());

        Map<String, String> attributesMap = userType.attributes().stream()
                .collect(Collectors.toMap(
                        AttributeType::name,
                        AttributeType::value
                ));
        dto.setAttributes(attributesMap);

        dto.setEmail(attributesMap.get("email"));

        return dto;
    }

    public String deleteUser(String username) {
        AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .build();
        try {
            cognitoClient.adminDeleteUser(deleteUserRequest);
            return "User deleted successfully";
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete user in Cognito", e);
        }
    }

    public String updateUserAttributes(String username, Map<String, String> attributes) {
        List<AttributeType> attributeTypes = attributes.entrySet().stream()
                .map(entry -> AttributeType.builder().name(entry.getKey()).value(entry.getValue()).build())
                .collect(Collectors.toList());

        AdminUpdateUserAttributesRequest updateUserAttributesRequest = AdminUpdateUserAttributesRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .userAttributes(attributeTypes)
                .build();

        try {
            cognitoClient.adminUpdateUserAttributes(updateUserAttributesRequest);
            return "User attributes updated successfully";
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user attributes in Cognito", e);
        }
    }
}
