package com.it.academy.services.impl;

import com.it.academy.enums.Provider;
import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import com.it.academy.exceptions.AppException;
import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("User not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Long save(User user) {
        user.setActivationToken(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(Role.ROLE_STUDENT);
        user.setProvider(Provider.LOCAL);

        return repo.save(user).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        User user = getById(id);
        user.setStatus(UserStatus.DELETED);
        return repo.save(user).getId();
    }

    @Override
    public Long updateById(Long id, User updatedUser) {
        User user = getById(id);

        user.setFullName(updatedUser.getFullName());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        return repo.save(user).getId();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<User> getByResetToken(String resetToken) {
        return repo.findByResetToken(resetToken);
    }

    @Override
    public void processOAuthPostLogin(String username, String name, String registrationId) {
        if (repo.findByEmail(username).isEmpty()) {
            User user = User.builder()
                    .role(Role.ROLE_STUDENT)
                    .provider(registrationId.equals("google")
                            ? Provider.GOOGLE
                            : Provider.GITHUB)
                    .fullName(name)
                    .email(username)
                    .status(UserStatus.ACTIVE)
                    .build();

            repo.save(user);
        }
    }

}
