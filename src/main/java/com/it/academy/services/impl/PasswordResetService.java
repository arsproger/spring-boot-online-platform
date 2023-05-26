package com.it.academy.services.impl;

import com.it.academy.config.EmailConfig;
import com.it.academy.exceptions.AppException;
import com.it.academy.models.User;
import com.it.academy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final UserService userService;
    private final EmailConfig emailConfig;
    private final PasswordEncoder passwordEncoder;
    @Value("${resetUrl}")
    private String resetUrl;

    public ResponseEntity<Map<String, String>> resetPassword(String email) {
        Optional<User> user = userService.getByEmail(email);
        if (user.isEmpty()) {
            throw new AppException("Пользователь не найден!", HttpStatus.NOT_FOUND);
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(30)); // установка срока действия токена
        userService.save(user.get());

        String reset = resetUrl + resetToken;
        String emailText = "Для сброса пароля перейдите по ссылке: " + reset;

        emailConfig.sendSimpleMessage(email, "Сброс пароля", emailText);

        Map<String, String> response = Map.of("message", "Письмо с восстановлением пароля отправлено на вашу почту");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> saveNewPassword(String resetToken, String newPassword) {
        Optional<User> user = userService.getByResetToken(resetToken);
        if (user.isEmpty() || user.get().getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            throw new AppException("Неверный токен!", HttpStatus.FORBIDDEN);

        user.get().setPassword(passwordEncoder.encode(newPassword));
        user.get().setResetToken(null);
        user.get().setResetTokenExpireTime(null);
        userService.save(user.get());
        Map<String, String> response = Map.of("message", "Пароль успешно изменен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
