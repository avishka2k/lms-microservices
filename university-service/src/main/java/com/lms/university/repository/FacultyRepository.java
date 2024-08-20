package com.lms.university.repository;

import com.lms.university.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
//    Faculty findById(long id);
    boolean existsByName(String name);
}
