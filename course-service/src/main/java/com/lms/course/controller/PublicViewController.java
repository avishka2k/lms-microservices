package com.lms.course.controller;

import com.lms.course.entity.Course;
import com.lms.course.exception.NotFoundException;
import com.lms.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public")
public class PublicViewController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // list course by department id
    @GetMapping("/courses/department/{departmentId}")
    public ResponseEntity<?> getCoursesByDepartmentId(@PathVariable Long departmentId) {
        try {
            List<Course> courses = courseService.getCoursesByDepartmentId(departmentId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
