package com.lms.announcement.repository;

import com.lms.announcement.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByType(String type);
}
