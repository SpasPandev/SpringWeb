package com.example.battleships.repository;

import com.example.battleships.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
}
