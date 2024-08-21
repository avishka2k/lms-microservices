package com.lms.applicants.repository;

import com.lms.applicants.entity.StudentApplicants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface StudentApplicantsRepository extends JpaRepository<StudentApplicants, Long> {
    boolean existsByEmail(String email);
}
