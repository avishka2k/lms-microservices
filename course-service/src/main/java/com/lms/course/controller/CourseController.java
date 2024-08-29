package com.lms.course.controller;

import com.lms.course.dto.CourseUpdateDto;
import com.lms.course.entity.Course;
import com.lms.course.entity.CModule;
import com.lms.course.exception.ConflictException;
import com.lms.course.exception.NotFoundException;
import com.lms.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    // Get all courses
    @GetMapping
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

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (ConflictException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateDto courseDetails) {
        try {
            return new ResponseEntity<>(courseService.updateCourse(id, courseDetails), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all modules
    @GetMapping("/module")
    public ResponseEntity<?> getAllModules() {
        try {
            List<CModule> CModules = courseService.getModules();
            return new ResponseEntity<>(CModules, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get module by ID
    @GetMapping("/module/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long id) {
        try {
            CModule module = courseService.getModuleById(id);
            return new ResponseEntity<>(module, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get faculty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new module
    @PostMapping("/module")
    public ResponseEntity<?> createModule(@RequestBody CModule module) {
        try {
            CModule createdModule = courseService.createModule(module);
            return new ResponseEntity<>(createdModule, HttpStatus.CREATED);
        } catch (ConflictException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create module", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a module
    @PutMapping("/module/{id}")
    public ResponseEntity<?> updateModule(@PathVariable Long id, @RequestBody CourseUpdateDto courseDetails) {
        try {
            return new ResponseEntity<>(courseService.updateModule(id, courseDetails), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update module", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a module
    @DeleteMapping("/module/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(courseService.deleteModule(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete module", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get modules by course ID
    @GetMapping("/module/{courseId}")
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

    // Assign module to course
    @PostMapping("/assign/{moduleId}/{courseId}")
    public ResponseEntity<?> assignModuleToCourse(@PathVariable Long moduleId, @PathVariable Long courseId) {
        try {
            return new ResponseEntity<>(courseService.assignModuleToCourse(moduleId, courseId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to assign module to course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Unassign module from course
    @PostMapping("/unassign/{moduleId}")
    public ResponseEntity<?> unassignModuleFromCourse(@PathVariable Long moduleId) {
        try {
            return new ResponseEntity<>(courseService.unassignModuleFromCourse(moduleId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to unassign module from course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unassigned")
    public ResponseEntity<?> getCoursesWithoutAssigned() {
        try {
            List<Course> courses = courseService.getCoursesWithoutAssigned();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get courses by department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<?> getCoursesByDepartmentId(@PathVariable Long departmentId) {
        try {
            List<Course> courses = courseService.getCoursesByDepartmentId(departmentId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get courses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/unassign/{courseId}/department")
    public ResponseEntity<?> unassignCourseFromDepartment(@PathVariable Long courseId) {
        try {
            return new ResponseEntity<>(courseService.unassignCourseFromDepartment(courseId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to unassign course from department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

