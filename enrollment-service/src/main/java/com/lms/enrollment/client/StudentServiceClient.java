package com.lms.enrollment.client;

import com.lms.enrollment.config.FeignConfig;
import com.lms.enrollment.dto.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Feign client interface for communicating with the user-service.
 */
@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface StudentServiceClient {
    /**
     * Retrieves a list of students from the user-service.
     */
    @GetMapping("/api/admin/getStudents")
    List<StudentResponse> getStudents();
}
