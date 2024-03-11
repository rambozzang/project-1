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
        .title("TigerBk Project1 Project")
        .description("이름:전범규 / 토큰 : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJtZW1iSWQiOiJ0aWdlcmJrIiwiaXNBZG1pbiI6Ik4iLCJhdXRoTWV0aG9kIjoiTiIsImV4cCI6MTcyNTYxOTcxOX0.-pQqbA2dbuKz8YajXiJumltqmTFIMIYboj3juO2Ic6obe1Pphnmr4APHUzYTZOsThyYnPknXVabkUrRPFl8X3w");

    Server server = new Server().url(servletContext.getContextPath());
    log.debug("########################################################################");
    log.debug("### App-Woori-Server OpenAPI30Configuration Configuration");
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
                .bearerFormat("JWT"))
        );
  }
}
