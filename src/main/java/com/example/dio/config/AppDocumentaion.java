package com.example.dio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class AppDocumentaion {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info().title("Dine in Order API")
                .description("""
                        ##Description
                        **Dine In Order** is an Api built using spring boot REST Architecture.
                        the API is designed to serve as a backend to a application that deals in ordering food online.
                        
                        ## Tech-Stack
                        - Java 8
                        - Spring Boot
                        - Spring Data JPA
                        - MySql Database
                        - Spring Security
                        """)
                .version("v1")
                .contact(contact());
    }
    private Contact contact(){
        return new Contact().email("nitishsinghz2000@gmail.com")
                .name("Nitish Singh")
                .url("https://github.com/nitish-nks");
    }
}
