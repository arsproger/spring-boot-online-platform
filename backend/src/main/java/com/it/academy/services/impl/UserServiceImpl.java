package com.it.academy.services.impl;

import com.it.academy.enums.Provider;
import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        user.setProvider(Provider.LOCAL);

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

        user.setFullName(updatedUser.getFullName());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void processOAuthPostLogin(String username, String name, String registrationId) {
        if (userRepository.findByEmail(username).isEmpty()) {
            User user = new User();
            user.setRole(Role.ROLE_STUDENT);
            user.setProvider(registrationId.equals("google")
                    ? Provider.GOOGLE
                    : Provider.GITHUB);
            user.setFullName(name);
            user.setEmail(username);
            user.setStatus(UserStatus.ACTIVE);

            userRepository.save(user);
        }
    }

}
