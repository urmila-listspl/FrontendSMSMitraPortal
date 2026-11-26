package com.list20.SMSMitraPortal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public WebConfig() {
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(new String[]{"https://lvf.listspl.com:1355/", "https://172.100.30.107:1355", "http://172.100.31.120:8888", "http://localhost:9098", "http://localhost:3000", "http://172.100.30.106:9098", "http://172.100.30.106:3000", "http://172.100.30.106:9098"})
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders(new String[]{"*"}).allowCredentials(true);
    }
}
