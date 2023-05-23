package com.it.academy.services.impl;

import com.it.academy.config.EmailConfig;
import com.it.academy.models.User;
import com.it.academy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final UserService userService;
    private final EmailConfig emailConfig;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> resetPassword(String email) {
        Optional<User> user = userService.getByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(30)); // установка срока действия токена
        userService.save(user.get());

        String resetUrl = "http://localhost:8080/password/reset/" + resetToken;
        String emailText = "Для сброса пароля перейдите по ссылке: " + resetUrl;

        emailConfig.sendSimpleMessage(email, "Сброс пароля", emailText);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> saveNewPassword(String resetToken, String newPassword) {
        Optional<User> user = userService.getByResetToken(resetToken);
        if (user.isEmpty() || user.get().getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return ResponseEntity.badRequest().build();

        user.get().setPassword(passwordEncoder.encode(newPassword));
        user.get().setResetToken(null);
        user.get().setResetTokenExpireTime(null);
        userService.save(user.get());

        return ResponseEntity.ok().build();
    }

}