package com.virasoftware.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.authservice.domains.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByActivationCode(String activationCode);
}
