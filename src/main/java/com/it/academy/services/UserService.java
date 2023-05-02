package com.it.academy.services;

import com.it.academy.enums.UserStatus;
import com.it.academy.models.Role;
import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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

        String activationLink = "http://localhost:8080/activate?email=" + user.getEmail() +
                "&token=" + user.getActivationToken();
        emailService.sendActivationEmail(user.getEmail(), activationLink);

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

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String activateAccount(String email, String token) {
        Optional<User> developer = userRepository.findByEmail(email);

        if (developer.isEmpty())
            throw new UsernameNotFoundException("Not found!");

        if (!developer.get().getActivationToken().equals(token))
            return "Неправильный токен активации";

        developer.get().setStatus(UserStatus.ACTIVE);
        userRepository.save(developer.get());

        return "Аккаунт успешно активирован!";
    }

    public ResponseEntity<?> resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(30)); // установка срока действия токена
        userRepository.save(user.get());

        String resetUrl = "http://localhost:8080/api/password/reset/" + resetToken;
        String emailText = "Для сброса пароля перейдите по ссылке: " + resetUrl;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return ResponseEntity.badRequest().build();

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

}
