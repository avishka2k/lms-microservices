package com.lms.course.controller;

import com.lms.course.entity.CModule;
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
@RequestMapping("/api/studentcourse")
public class StudentCourseController {

    private final CourseService courseService;

    @GetMapping("/module/{courseId}/course")
    public ResponseEntity<?> getModulesByCourseId(@PathVariable Long courseId) {
        try {
            List<CModule> modules = courseService.getModulesByCourseId(courseId);
            return new ResponseEntity<>(modules, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get modules", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
