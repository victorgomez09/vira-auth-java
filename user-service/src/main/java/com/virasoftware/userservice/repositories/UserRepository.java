package com.virasoftware.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.userservice.domains.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
