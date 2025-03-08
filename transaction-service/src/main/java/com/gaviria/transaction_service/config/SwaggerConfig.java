package com.gaviria.transaction_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transactions Service API")
                        .description("""
                                    Esta API gestiona las transacciones dentro del sistema, proporcionando operaciones
                                    para la creación y consulta de transacciones.

                                    Este servicio simula transacciones cada 5 segundos por el topic transaction-events
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Cristian Gaviria")
                                .email("cristian@gaviria.org")
                                .url("https://gaviria.org")));

    }
}
