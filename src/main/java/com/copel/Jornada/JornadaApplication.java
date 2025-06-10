package com.copel.Jornada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JornadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JornadaApplication.class, args);
    }
}
