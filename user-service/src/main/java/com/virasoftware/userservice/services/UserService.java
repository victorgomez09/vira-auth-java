package com.virasoftware.userservice.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.virasoftware.userservice.domains.dtos.EmailUpdateDto;
import com.virasoftware.userservice.domains.entities.User;
import com.virasoftware.userservice.domains.exceptions.UserNotFoundException;
import com.virasoftware.userservice.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(Long userId) {
        User user = findById(userId);

        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with Username: " + username));
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User updateUserById(Long userId, User dto) {
        User user = findById(userId);
        user.setFirstName(dto.getFirstName()); // id, username - cannot change; email - by patch with verification;
                                               // roles - by user with authorities
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        
        return userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    public User patchUserById(Long userId, User dto) {
        User user = findById(userId);
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getPhone() != null) {
            user.setPhone(user.getPhone());
        }
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with UserID: " + id));
    }

    public void updateEmail(EmailUpdateDto emailUpdateDto) {
        User user = findById(emailUpdateDto.getUserId());
        user.setEmail(emailUpdateDto.getEmail());
        userRepository.save(user);
    }
}
