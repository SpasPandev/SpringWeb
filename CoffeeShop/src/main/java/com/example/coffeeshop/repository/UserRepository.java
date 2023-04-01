package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User AS u JOIN Order AS o ORDER BY SIZE(u.orders) DESC ")
    List<User> findAllOrderByEmployeesCountOfOrdersDesc();
}
