package com.bandeira.sistema_venda_de_ingressos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {


    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI().info(
                new Info().description("Definição de Apis para ms Validações")
                        .version("1.0.0")
                        .title("Validações Api venda de ingressos")
        );
    }
}
