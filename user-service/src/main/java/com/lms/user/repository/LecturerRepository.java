package com.lms.user.repository;

import com.lms.user.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Lecturer findByUsername(String username);
}
