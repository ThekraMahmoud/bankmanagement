
package com.example.bankmanagement.Config;

import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class AdminInitializer {

    private final AuthUserRepository userRepository;

    @Bean
    public CommandLineRunner initAdmin(PasswordEncoder passwordEncoder) {

        return args -> {
            if (userRepository.findUserByUserName("ADMIN") == null) {

                User admin = new User();

                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("Admin12345"));
                admin.setName("System Admin");
                admin.setEmail("admin@example.com");
                admin.setRole("ADMIN");

                userRepository.save(admin);
            }
        };
    }
}
