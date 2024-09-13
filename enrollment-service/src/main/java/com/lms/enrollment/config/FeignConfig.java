package com.lms.enrollment.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * Configuration class for setting up Feign client-related beans.
 * Provides custom configuration for Feign clients to include OAuth2 tokens in request headers.
 */
@Configuration
public class FeignConfig {
    /**
     * Creates a {@link RequestInterceptor} bean for Feign clients.
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getToken() != null) {
                requestTemplate.header("Authorization", "Bearer " + authentication.getToken().getTokenValue());
            }
        };
    }

}
