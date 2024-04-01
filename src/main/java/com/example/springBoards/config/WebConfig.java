package com.example.springBoards.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String resourcePath = "/upload/**";
        String savePath = "file:///C:/work/springBoards/src/main/resources/static/spring_img/";
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }

}
