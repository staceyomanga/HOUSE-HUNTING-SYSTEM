package com.hostelfinder.config;

import com.hostelfinder.entity.User;
import com.hostelfinder.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Check if admin exists
            if (userRepository.findByEmail("admin@hostelfinder.com").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setFullName("System Admin");
                admin.setEmail("admin@hostelfinder.com");
                admin.setPasswordHash(encoder.encode("Admin@123")); // Secure password
                admin.setRole("ADMIN");
                admin.setPhone("0700000000");

                userRepository.save(admin);
                System.out.println("✅ Default Admin created: admin@hostelfinder.com / Admin@123");
            }
        };
    }
}