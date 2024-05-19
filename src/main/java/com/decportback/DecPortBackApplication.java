package com.decportback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DecPortBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecPortBackApplication.class, args);
    }

}
