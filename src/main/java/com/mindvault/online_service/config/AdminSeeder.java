package com.mindvault.online_service.config;

import com.mindvault.online_service.entities.RoleEnum;
import com.mindvault.online_service.entities.User;
import com.mindvault.online_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner seedAdmin() {
        return args -> {
            if (userRepository.existsByEmail("admin@system.com")) {
                return;
            }

            User admin = User.builder()
                    .email("admin@system.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("System Admin")
                    .role(RoleEnum.ADMIN)
                    .enabled(true)
                    .build();

            userRepository.save(admin);

            System.out.println("✅ Admin user created");
        };
    }
}
