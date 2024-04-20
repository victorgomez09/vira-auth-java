package com.virasoftware.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.authservice.domains.entities.AuthUser;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByActivationCode(String activationCode);
    Optional<AuthUser> findByUserId(Long userId);
}
