package com.lms.enrollment.client;

import com.lms.enrollment.config.FeignConfig;
import com.lms.enrollment.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * Feign client for interacting with the University Service.
 * Provides methods for retrieving course information from the university service.
 */
@FeignClient(name = "university-service", configuration = FeignConfig.class)
public interface UniversityServiceClient {
    /**
     * Retrieves a list of courses from the university service.
     */
    @GetMapping("/api/uni/getCourses")
    List<CourseResponse> getCourses();
}
