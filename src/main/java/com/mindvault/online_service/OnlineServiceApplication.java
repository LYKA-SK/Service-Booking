package com.mindvault.online_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnlineServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Print clickable Swagger UI link in terminal
        System.out.println("\n🎉 Swagger UI is ready! Open it in your browser: http://localhost:8080/swagger-ui/index.html\n");
    }
}
