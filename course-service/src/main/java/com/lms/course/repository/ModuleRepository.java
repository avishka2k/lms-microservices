package com.lms.course.repository;

import com.lms.course.entity.CModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModuleRepository extends JpaRepository<CModule, Long> {
    boolean existsByTitle(String title);
    List<CModule> findByCourseId(Long courseId);
    List<CModule> findByCourseIsNull();
}
