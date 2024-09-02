package com.lms.user.service.impl;

import com.lms.user.dto.CognitoUserDto;
import com.lms.user.dto.LecturerRequestDto;
import com.lms.user.dto.StudentRequestDto;
import com.lms.user.dto.UserRequestDto;
import com.lms.user.entity.Address;
import com.lms.user.entity.Lecturer;
import com.lms.user.entity.Student;
import com.lms.user.repository.AddressRepository;
import com.lms.user.repository.LecturerRepository;
import com.lms.user.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private CognitoIdentityProviderClient cognitoClient;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    public String createStudent(StudentRequestDto dto, String groupName) {

        Address address = new Address();
        address.setAddressLine1(dto.getAddress().getAddressLine1());
        address.setAddressLine2(dto.getAddress().getAddressLine2());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());

        try {
            address = addressRepository.save(address);
            Student student = getStudent(dto, address);
            studentRepository.save(student);
            System.out.println("Student created successfully with username " + dto);
//            String user = createCognitoUser(dto, groupName);
            return "Student created successfully with username ";
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student in database" + e);
        }
    }

    public String createLecturer(LecturerRequestDto dto, String groupName) {

        Address address = new Address();
        address.setAddressLine1(dto.getAddress().getAddressLine1());
        address.setAddressLine2(dto.getAddress().getAddressLine2());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());

        try {
            address = addressRepository.save(address);
            Lecturer student = getLecturer(dto, address);
            lecturerRepository.save(student);
//            String user = createCognitoUser(dto, groupName);
            return "Lecturer created successfully with username ";
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student in database" + e);
        }
    }

    private static Student getStudent(StudentRequestDto dto, Address address) {
        Student student = new Student();
        student.setUsername(dto.getUsername());
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setFullName(dto.getFullName());
        student.setPhone(dto.getPhone());
        student.setAddress(address);
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setNicImage(dto.getNicImage());
        student.setStudentImage(dto.getStudentImage());
        student.setBirthCertificateImage(dto.getBirthCertificateImage());
        student.setStudentId(dto.getStudentId());
        student.setEnrollmentNumber(dto.getEnrollmentNumber());
        student.setIntake(dto.getIntake());
        student.setGuardianName(dto.getGuardianName());
        student.setGuardianPhone(dto.getGuardianPhone());
        student.setGuardianEmail(dto.getGuardianEmail());
        student.setGuardianRelationship(dto.getGuardianRelationship());
        return student;
    }

    private static Lecturer getLecturer(LecturerRequestDto dto, Address address) {
        Lecturer lecturer = new Lecturer();
        lecturer.setUsername(dto.getUsername());
        lecturer.setEmail(dto.getEmail());
        lecturer.setFirstName(dto.getFirstName());
        lecturer.setLastName(dto.getLastName());
        lecturer.setFullName(dto.getFullName());
        lecturer.setPhoneNumber(dto.getPhoneNumber());
        lecturer.setAddress(address);
        lecturer.setDateOfBirth(dto.getDateOfBirth());
        lecturer.setGender(dto.getGender());

        lecturer.setNicImage(dto.getNicImage());
        lecturer.setProfileImage(dto.getProfileImage());
        lecturer.setLecturerId(dto.getLecturerId());
        lecturer.setDesignation(dto.getDesignation());
        lecturer.setDepartment(dto.getDepartment());
        lecturer.setFaculty(dto.getFaculty());
        lecturer.setOfficeLocation(dto.getOfficeLocation());
        lecturer.setEmployType(dto.getEmployType());
        lecturer.setNic(dto.getNic());
        lecturer.setHighestDegreeObtained(dto.getHighestDegreeObtained());
        lecturer.setHighestDegreeInstitute(dto.getHighestDegreeInstitute());
        lecturer.setSpecialization(dto.getSpecialization());
        lecturer.setResearchInterest(dto.getResearchInterest());
        lecturer.setLinkedIn(dto.getLinkedIn());
        lecturer.setCv(dto.getCv());
        return lecturer;
    }

    public String createCognitoUser(UserRequestDto dto, String groupName) {

        String username = dto.getUsername();
        String email = dto.getEmail();
        String name = dto.getFullName();

        try {

            AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .userAttributes(Arrays.asList(
                            AttributeType.builder().name("email").value(email).build(),
                            AttributeType.builder().name("email_verified").value("false").build(),
                            AttributeType.builder().name("name").value(name).build()
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
