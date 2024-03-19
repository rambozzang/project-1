package com.tigerbk.project1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class OAS3Config {

  @Bean
  public OpenAPI customizeOpenAPI(ServletContext servletContext) {

    final String securitySchemeName = "bearerAuth";
    Info info = new Info()
        .version("v1.0.0")
        .title("Kos Project1 Project")
        .description("이름: 홍길동  / 토큰 : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJjdXN0SWQiOiJ0aWdlcmJrIiwiaXNBZG1pbiI6Ik4iLCJhdXRoTWV0aG9kIjoiTiIsImV4cCI6MTcyNjM1NzgxNH0.PcNWCrdjQiJq4nDAB7zWtKVvEyeYx5Cos1FPLDbpYPxnHhmWF9ZSUkbmQ7PR5-MeOsFs1g9G3EtF9CBDqUZ8sQ");

    Server server = new Server().url(servletContext.getContextPath());
    log.debug("########################################################################");
    log.debug("### Project1-Server OpenAPI30Configuration Configuration");
    log.debug("########################################################################");
    return new OpenAPI()
        .info(info)
        .servers(List.of(server))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
                new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                )
        );
  }
}
