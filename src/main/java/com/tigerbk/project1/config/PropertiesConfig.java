package com.tigerbk.project1.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {
        "classpath:application-${spring.profiles.active}.yml" ,
        "classpath:application-oauth.yml"
}, factory = YamlPropertySourceFactory.class)
public class PropertiesConfig {
}
