package com.mindvault.online_service.config;

import com.mindvault.online_service.entities.Category;
import com.mindvault.online_service.entities.RoleEnum;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.CategoryRepository;
import com.mindvault.online_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // 1. Create Admin
            User admin = User.builder()
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("System Admin")
                    .role(RoleEnum.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);

            // 2. Create Categories linked to that Admin
            categoryRepository.save(Category.builder()
                    .name("Cleaning")
                    .description("Home cleaning services")
                    .user(admin)
                    .build());

            categoryRepository.save(Category.builder()
                    .name("Plumbing")
                    .description("Pipe and water services")
                    .user(admin)
                    .build());

            System.out.println("✅ Data Seeded Successfully!");
        }
    }
}