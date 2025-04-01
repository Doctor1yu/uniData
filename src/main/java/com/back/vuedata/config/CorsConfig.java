package com.back.vuedata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9999") // 允许的源
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH") // 允许的HTTP方法
                .allowedHeaders("Content-Type", "Authorization") // 允许的请求头
                .maxAge(1728000); // 预检请求缓存时间
    }
} 