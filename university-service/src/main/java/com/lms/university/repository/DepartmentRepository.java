package com.lms.university.repository;

import com.lms.university.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
    List<Department> findByFacultyId(Long facultyId);
    // findByFacultyIsNull
    List<Department> findByFacultyIsNull();
}
