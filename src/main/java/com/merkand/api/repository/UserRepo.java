package com.merkand.api.repository;

import com.merkand.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndActiveTrue(String username);
    List<User> findAllByActiveTrue();
}

