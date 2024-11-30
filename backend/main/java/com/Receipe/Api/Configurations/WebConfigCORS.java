package com.Receipe.Api.Configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigCORS implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Enable CORS for all APIs
        registry.addMapping("/api/**") // Enable for all endpoints under "/api"
                .allowedOrigins("http://localhost:3000") // The frontend URL (e.g., React app running on port 3000)
                .allowedMethods("GET", "POST") // Allow the necessary HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow cookies to be sent with requests
    }
}
