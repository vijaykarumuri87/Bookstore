package com.itsync.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Bookstore API")
                .version("1.0")
                .description("REST API for managing books in a bookstore."));
    }

    @Bean
    public GroupedOpenApi bookstoreOpenApi() {
        return GroupedOpenApi.builder()
                .group("bookstore")
                .packagesToScan("com.itsync.controller")
                .build();
    }

}

