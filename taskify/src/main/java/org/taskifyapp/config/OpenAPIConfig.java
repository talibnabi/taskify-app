package org.taskifyapp.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import static org.taskifyapp.util.OpenAPIConfigConstants.*;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return openApi()
                .addSecurityItem(securityRequirement().addList(SECURITY_SCHEME_NAME)).components(components().addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(SCHEME)
                        .bearerFormat(BEARER_FORMAT))).info(info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                );
    }

    private Components components() {
        return new Components();
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement();
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme();
    }

    private OpenAPI openApi() {
        return new OpenAPI();
    }

    private Info info() {
        return new Info();
    }
}
