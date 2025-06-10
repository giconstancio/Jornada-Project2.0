package com.copel.Jornada.Seeders;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.copel.Jornada.User.UserRepository;
import com.copel.Jornada.User.User;

@Component
public class UserSeedCommand {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeedCommand(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void seedAdminUser() {
        if (userRepository.count() > 0) return;

        User user = new User();
        user.setName("Administrador");
        user.setEmail("giovana2005constancio@gmail.com");
        user.setPassword(passwordEncoder.encode("AdminCopel2025Senai"));

        userRepository.save(user);

        System.out.println("✅ Usuário administrador criado com sucesso.");
    }
}
