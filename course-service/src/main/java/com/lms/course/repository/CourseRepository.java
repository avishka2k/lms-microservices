package com.lms.course.repository;
import com.lms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitle(String title);
    List<Course> findByDepartmentId(Long departmentId);
    List<Course> findByDepartmentIdIsNull();


}