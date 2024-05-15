package com.decportback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DecPortBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecPortBackApplication.class, args);
    }

}
