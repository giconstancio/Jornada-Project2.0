package com.copel.Jornada.Config;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.copel.Jornada.User.UserRepository;
import com.copel.Jornada.User.User;

public class DataInitializer implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private final User user;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, User user, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.user = user;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setSlug(UUID.randomUUID().toString());
            user.setName("Administrador");
            user.setEmail("giovana2005constancio@gmail.com");
            user.setPassword(passwordEncoder.encode("AdminCopel2025Senai"));

            userRepository.save(user);

            System.out.println("Usuário inicial criado com senha criptografada!");
        }
    }
}
