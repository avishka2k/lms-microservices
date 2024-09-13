package com.lms.university.service;

import com.lms.university.dto.DepartmentUpdateDto;
import com.lms.university.dto.FacultyUpdateDto;
import com.lms.university.entity.Department;
import com.lms.university.entity.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> listFaculties();
    Faculty getFacultyById(Long id);
    Faculty createFaculty(Faculty faculty);
    Faculty updateFaculty(Long id, FacultyUpdateDto facultyUpdateDto);
    String deleteFaculty(Long id);
    List<Department> getDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, DepartmentUpdateDto departmentUpdateDto);
    String deleteDepartment(Long id);
    List<Department> getDepartmentsByFacultyId(Long facultyId);
    String assignDepartmentToFaculty(Long departmentId, Long facultyId);
    String unassignDepartmentFromFaculty(Long departmentId);
    List<Department> listDepartmentWithoutFaculty();
    String assignCourseToDepartment(Long departmentId, Long courseId);
    String unassignCourseFromDepartment(Long departmentId, Long courseId);

}
