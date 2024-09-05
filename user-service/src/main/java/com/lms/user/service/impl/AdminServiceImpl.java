package com.lms.user.service.impl;

import com.lms.user.dto.*;
import com.lms.user.entity.Address;
import com.lms.user.entity.Lecturer;
import com.lms.user.entity.Student;
import com.lms.user.exception.NotFoundException;
import com.lms.user.repository.AddressRepository;
import com.lms.user.repository.LecturerRepository;
import com.lms.user.repository.StudentRepository;
import com.lms.user.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

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
            String user = createCognitoUser(dto, groupName);
            return "Student created successfully with username " + user;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student in database" + e);
        }
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String updateStudent(Long id, StudentUpdateDto dto) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new NotFoundException("Student not found with id " + id);
        }

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setFullName(dto.getFullName());
        student.setPhone(dto.getPhone());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());

        Address address = student.getAddress();
        address.setAddressLine1(dto.getAddress().getAddressLine1());
        address.setAddressLine2(dto.getAddress().getAddressLine2());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());

        try {
            addressRepository.save(address);
            studentRepository.save(student);
            updateUserAttributes(student.getUsername(), Map.of(
                    "name", student.getFullName()
            ));
            return "Student updated successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student in database" + e);
        }
    }

    public String deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new NotFoundException("Student not found with id " + id);
        }

        try {
            studentRepository.delete(student);
            deleteCognitoUser(student.getUsername());
            return "Student deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete student in database" + e);
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
            String user = createCognitoUser(dto, groupName);
            return "Lecturer created successfully with username " + user;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student in database" + e);
        }
    }

    public Lecturer getLecturerById(Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    public Lecturer getLecturerByUsername(String username) {
        return lecturerRepository.findByUsername(username);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public String updateLecturer(Long id, LecturerUpdateDto dto) {
        Lecturer lecturer = lecturerRepository.findById(id).orElse(null);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer not found with id " + id);
        }

        lecturer.setFirstName(dto.getFirstName());
        lecturer.setLastName(dto.getLastName());
        lecturer.setFullName(dto.getFullName());
        lecturer.setPhone(dto.getPhone());
        lecturer.setDateOfBirth(dto.getDateOfBirth());
        lecturer.setGender(dto.getGender());

        Address address = lecturer.getAddress();
        address.setAddressLine1(dto.getAddress().getAddressLine1());
        address.setAddressLine2(dto.getAddress().getAddressLine2());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setCountry(dto.getAddress().getCountry());

        try {
            addressRepository.save(address);
            lecturerRepository.save(lecturer);
            updateUserAttributes(lecturer.getUsername(), Map.of(
                    "name", lecturer.getFullName()
            ));
            return "Lecturer updated successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update lecturer in database" + e);
        }
    }

    public String deleteLecturer(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id).orElse(null);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer not found with id " + id);
        }

        try {
            lecturerRepository.delete(lecturer);
            deleteCognitoUser(lecturer.getUsername());
            return "Lecturer deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete lecturer in database" + e);
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


    private void updateUserAttributes(String username, Map<String, String> attributes) {
        try {
            List<AttributeType> attributeTypes = attributes.entrySet().stream()
                    .map(entry -> AttributeType.builder().name(entry.getKey()).value(entry.getValue()).build())
                    .collect(Collectors.toList());

            AdminUpdateUserAttributesRequest updateUserAttributesRequest = AdminUpdateUserAttributesRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .userAttributes(attributeTypes)
                    .build();

            cognitoClient.adminUpdateUserAttributes(updateUserAttributesRequest);
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user attributes in Cognito", e);
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
        lecturer.setPhone(dto.getPhone());
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
        lecturer.setWorkType(dto.getWorkType());
        lecturer.setNic(dto.getNic());
        lecturer.setHighestDegree(dto.getHighestDegree());
        lecturer.setInstitution(dto.getInstitution());
        lecturer.setMajor(dto.getMajor());
        lecturer.setResearchInterest(dto.getResearchInterest());
        lecturer.setLinkedIn(dto.getLinkedIn());
        lecturer.setCv(dto.getCv());
        return lecturer;
    }

    private String createCognitoUser(UserRequestDto dto, String groupName) {

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

    private void deleteCognitoUser(String username) {
        try {
            AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .build();
            cognitoClient.adminDeleteUser(deleteUserRequest);
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found with username " + username);
        } catch (CognitoIdentityProviderException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete user in Cognito", e);
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
}
