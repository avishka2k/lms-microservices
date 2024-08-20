package com.lms.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "group")
public class ConfigProperties {
        private String student;
        private String lecturer;
}
