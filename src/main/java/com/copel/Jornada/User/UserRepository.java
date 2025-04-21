package com.copel.Jornada.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findBySlug(String slug);
    void deleteBySlug(String slug);

}
