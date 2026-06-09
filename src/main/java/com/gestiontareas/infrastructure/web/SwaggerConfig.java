package com.gestiontareas.infrastructure.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger / OpenAPI.
 * Personaliza la información que aparece en la interfaz de documentación.
 */

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Gestión de Tareas API")
                .description("API REST para gestión de tareas con arquitectura hexagonal y DDD")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Fran Soria")
                    .email("fran@gestiontareas.com")
                ));
    }
}