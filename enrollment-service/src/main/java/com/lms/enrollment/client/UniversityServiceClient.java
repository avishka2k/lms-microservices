package com.lms.enrollment.client;

import com.lms.enrollment.config.FeignConfig;
import com.lms.enrollment.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "university-service", configuration = FeignConfig.class)
public interface UniversityServiceClient {

    @GetMapping("/api/uni/getCourses")
    List<CourseResponse> getCourses();
}
