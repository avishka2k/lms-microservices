package com.lms.user.service;

import com.lms.user.dto.*;
import com.lms.user.entity.Lecturer;
import com.lms.user.entity.Student;
import java.util.List;
import java.util.Map;

public interface AdminService {
    String createStudent(StudentRequestDto dto, String groupName);
    Student getStudentById(Long id);
    Student getStudentByUsername(String username);
    List<Student> getAllStudents();
    String updateStudent(Long id, StudentUpdateDto dto);
    String deleteStudent(Long id);
    String createLecturer(LecturerRequestDto dto, String groupName);
    Lecturer getLecturerById(Long id);
    Lecturer getLecturerByUsername(String username);
    List<Lecturer> getAllLecturers();
    String updateLecturer(Long id, LecturerUpdateDto dto);
    String deleteLecturer(Long id);
    List<CognitoUserDto> listUsersInGroup(String groupName);
    Map<String, String> getUserByUsername(String username);
}
