package com.lms.course.Service;

import com.lms.course.Dto.CourseDto;
import com.lms.course.Entity.Course;
import com.lms.course.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
        CourseDto courseDTO = new CourseDto();
        courseDTO.setTitle(course.getTitle());
        courseDTO.setLecturer(course.getLecturer());
        courseDTO.setDuration(course.getDuration());
        courseDTO.setLevel(course.getLevel());
        courseDTO.setLanguage(course.getLanguage());

        return courseDTO;
    }
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));

        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setLecturer(courseDetails.getLecturer());
        course.setDuration(courseDetails.getDuration());
        course.setLevel(courseDetails.getLevel());
        course.setLanguage(courseDetails.getLanguage());
        course.setFormat(courseDetails.getFormat());
        course.setCredits(courseDetails.getCredits());

        Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));

        courseRepository.delete(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
