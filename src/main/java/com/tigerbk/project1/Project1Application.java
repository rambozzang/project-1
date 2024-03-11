package com.tigerbk.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.tigerbk.project1.config.AppConfigList;

@EnableJpaAuditing
@SpringBootApplication
@Import(AppConfigList.class)
public class Project1Application {
    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }
}
