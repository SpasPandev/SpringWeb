package com.example.shedules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShedulesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShedulesApplication.class, args);
    }

}
