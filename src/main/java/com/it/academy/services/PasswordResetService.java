package com.it.academy.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface PasswordResetService {
    ResponseEntity<Map<String, String>> resetPassword(String email);
    ResponseEntity<Map<String, String>> saveNewPassword(String resetToken, String newPassword);



}
