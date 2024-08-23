package com.lms.course.service;

import com.lms.course.dto.CourseUpdateDto;
import com.lms.course.entity.Course;
import com.lms.course.exception.NotFoundException;
import com.lms.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        if (courseRepository.existsByTitle(course.getTitle())) {
            throw new RuntimeException("Course already exists");
        }
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Faculty not found");
        }
        return course;
    }

    public Course updateCourse(Long id, CourseUpdateDto courseUpdateDto) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found with id " + id);
        }
        course.setTitle(courseUpdateDto.getTitle());
        course.setDescription(courseUpdateDto.getDescription());
        course.setLecturer(courseUpdateDto.getLecturer());
        course.setDuration(courseUpdateDto.getDuration());
        course.setLevel(courseUpdateDto.getLevel());
        course.setLanguage(courseUpdateDto.getLanguage());
        course.setFormat(courseUpdateDto.getFormat());
        course.setCredits(courseUpdateDto.getCredits());

        return courseRepository.save(course);
    }

    public String deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        courseRepository.delete(course);
        return "Faculty deleted successfully";
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
