package com.it.academy.services.impl;

import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public Long save(User user) {
        user.setActivationToken(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING);
        user.setRole(Role.ROLE_STUDENT);

        return userRepository.save(user).getId();
    }

    @Override
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateById(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id));

        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        return userRepository.save(user).getId();
    }

}
