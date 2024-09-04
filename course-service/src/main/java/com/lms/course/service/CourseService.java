package com.lms.course.service;


import com.lms.course.dto.CourseUpdateDto;
import com.lms.course.dto.ModuleUpdateDto;
import com.lms.course.entity.CModule;
import com.lms.course.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    Course getCourseById(Long id);
    Course updateCourse(Long id, CourseUpdateDto courseUpdateDto);
    String deleteCourse(Long id);
    List<Course> getAllCourses();
    List<CModule> getModules();
    CModule getModuleById(Long id);
    CModule createModule(CModule module);
    CModule updateModule(Long id, ModuleUpdateDto moduleUpdateDto);
    String deleteModule(Long id);
    List<CModule> getModulesByCourseId(Long courseId);
    String assignModuleToCourse(Long moduleId, Long courseId);
    List<CModule> getModulesWithoutAssigned();
    String unassignModuleFromCourse(Long moduleId);
    List<Course> getCoursesWithoutAssigned();
    List<Course> getCoursesByDepartmentId(Long departmentId);
    String unassignCourseFromDepartment(Long courseId);
    String getCourseNameById(Long courseId);
}
