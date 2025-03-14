package com.idsargus.akpmsadminservice.Mvc.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(
                new Info().title("AKPMS Admin Service")
                        .description("Admin Service 2.0 Mvc ")
        );
    }
}
