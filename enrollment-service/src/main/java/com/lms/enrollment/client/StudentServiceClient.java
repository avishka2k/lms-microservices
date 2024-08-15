package com.lms.enrollment.client;

import com.lms.enrollment.config.FeignConfig;
import com.lms.enrollment.dto.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface StudentServiceClient {

    @GetMapping("/api/admin/getStudents")
    List<StudentResponse> getStudents();
}
