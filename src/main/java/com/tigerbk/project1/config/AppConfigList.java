package com.tigerbk.project1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfig.class, OAS3Config.class, PropertiesConfig.class})
public class AppConfigList {}
