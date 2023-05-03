package com.it.academy.services;

import com.it.academy.enums.UserStatus;
import com.it.academy.models.Role;
import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Long save(User user) {
        user.setActivationToken(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING);
        user.setRole(Role.ROLE_STUDENT);

        return userRepository.save(user).getId();
    }

    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;

        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        return userRepository.save(user).getId();
    }

}
