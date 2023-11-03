package org.taskifyapp.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "BearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info().title("Taskify Application")
                        .description("eTaskify, is a cloud based enterprise task manager platform for organizations where companies\n" +
                                "can manage their daily tasks online. eTaskify wants to hire you as a rockstar back-end\n" +
                                "developer who will help build their first Mobile App Minimum Viable Product (MVP). You’ll be\n" +
                                "expected to build the back-end of the mobile app, while the front-end will be built by other\n" +
                                "developers in the team.")
                        .version("1.0"));
    }
}
