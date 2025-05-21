package com.copel.Jornada.Auth;

import com.copel.Jornada.User.User;
import com.copel.Jornada.User.UserRepository;
import com.copel.Jornada.Auth.dto.LoginRequest;
import com.copel.Jornada.Auth.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
