package com.sistemareservas_reservasvehiculos.aplication.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsGlobalConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {

        @Override
        public void addCorsMappings (CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") // Reemplaza con la URL de tu frontend
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        }

    };
    }
}