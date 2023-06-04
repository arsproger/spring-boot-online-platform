package com.it.academy.services.impl;

import com.it.academy.dao.UserDao;
import com.it.academy.entities.User;
import com.it.academy.exceptions.AppException;
import com.it.academy.services.EmailService;
import com.it.academy.services.PasswordResetService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Value("${resetUrl}")
    private String resetUrl;

    @Override
    public ResponseEntity<Map<String, String>> resetPassword(String email) {
        Optional<User> user = userService.getByEmail(email);
        if (user.isEmpty()) {
            throw new AppException("Пользователь с такой почтой не найден!", HttpStatus.NOT_FOUND);
        }

        String resetToken = UUID.randomUUID().toString();
        LocalDateTime resetTokenExpireTime = LocalDateTime.now().plusMinutes(30);
        userDao.updateUserResetTokenByEmail(email, resetToken, resetTokenExpireTime);

        String reset = resetUrl + "?token=" + resetToken;
        String emailText = "Для сброса пароля перейдите по ссылке: " + reset;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);

        Map<String, String> response = Map.of("message", "Письмо с восстановлением пароля отправлено на вашу почту");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> saveNewPassword(String resetToken, String newPassword) {
        Optional<User> user = userService.getByResetToken(resetToken);
        if (user.isEmpty() || user.get().getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            throw new AppException("Неверный токен!", HttpStatus.FORBIDDEN);

        String password = passwordEncoder.encode(newPassword);
        userDao.updateUserResetTokenAfterReset(password, resetToken);

        Map<String, String> response = Map.of("message", "Пароль успешно изменен");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
