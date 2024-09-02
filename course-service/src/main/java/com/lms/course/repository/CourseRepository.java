package com.lms.course.repository;
import com.lms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitle(String title);
    List<Course> findByDepartmentId(Long departmentId);
    List<Course> findByDepartmentIdIsNull();
}