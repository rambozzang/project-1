package com.tigerbk.project1.config;


import com.tigerbk.project1.security.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import com.tigerbk.project1.security.JwtExceptionFilter;
import com.tigerbk.project1.security.JwtFilter;


import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.application.name}")
    private String appName;

    private final JwtFilter jwtFilter;
    private final AuthenticationEntryPoint authEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final String[] SWAGGER = {
            "/api-docs",              "/swagger-ui/**",
            "/api/api-docs",          "/api-docs/swagger-config",
            "/api-docs.yaml",         "/api/api-docs/swagger-config",
            "/swagger-resources/**",  "/configuration/security",
            "/webjars/**",            "/swagger-ui.html"
    };
    private final String[] UI = {
            "/oauth2/**",              "/auth/**",
            "/css/**",                "/img/**",
            "/static/**",             "/resources/**",
            "/comm/code/**",          "/asis/**",
            "/cus/**",                "/cntr/**",
            "/view/**"
    };
    
 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("#########    ----------- Auth SecurityFilterChain --------------  ######");
        log.debug("#########    ----------- Application Name : "+ appName + " --------------  ######");
        http.httpBasic(httpBasic -> httpBasic.disable())
                .csrf((csrf) -> csrf.disable())
                .headers(headers -> headers.frameOptions(
                        frameOptions -> frameOptions.disable()
                ))
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of(
                            "https://localhost",
                            "https://www.tigerbk.com",
                            "https://project1.tigerbk.com",
                            "https://project2.tigerbk.com",
                            "https://project3.tigerbk.com",
                            "https://project4.tigerbk.com",
                            "https://project5.tigerbk.com",
                            "https://project6.tigerbk.com",
                            "https://project7.tigerbk.com",
                            "https://project8.tigerbk.com",
                            "https://project9.tigerbk.com",
                            "https://project10.tigerbk.com"
                    ));
                    corsConfig.applyPermitDefaultValues();
                    corsConfig.setAllowedMethods(List.of("GET","POST"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    return corsConfig;
                }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(UI).permitAll()
                                .requestMatchers(SWAGGER).permitAll()
                                .requestMatchers("/**").authenticated()

                )
                .exceptionHandling(
                    exceptionHandling -> exceptionHandling
                                .accessDeniedHandler(customAccessDeniedHandler)
                                .authenticationEntryPoint(authEntryPoint)
                );



        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtExceptionFilter(), JwtFilter.class);

        return http.build();
    }

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter();
    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
//        accessDeniedHandler.setErrorPage("/denied");
//        return accessDeniedHandler;
//    }


}