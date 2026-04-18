package com.example.swagger_study.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("TEST API 목록").description("학습용 API 입니다.").version("v1.0.0"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("개발용 서버임.")
                ));
    }
}
