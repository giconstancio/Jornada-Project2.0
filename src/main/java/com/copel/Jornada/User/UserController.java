package com.copel.Jornada.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/getBy/{slug}")
    public Optional<User> getBySlug(@PathVariable String slug) {
        return userRepository.findBySlug(slug);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/update/{slug}")
    public User updateUser(@PathVariable String slug, @RequestBody User newUser) {
        return userRepository.findBySlug(slug)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    @DeleteMapping("/delete/{slug}")
    public void deleteUser(@PathVariable String slug) {
        userRepository.deleteBySlug(slug);
    }
}

