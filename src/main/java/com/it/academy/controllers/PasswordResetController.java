package com.it.academy.controllers;

import com.it.academy.services.impl.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/reset")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestParam("email") String email) {
        return passwordResetService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public ResponseEntity<Map<String, String>> saveNewPassword(@PathVariable("resetToken") String resetToken, @RequestParam String newPassword) {
        return passwordResetService.saveNewPassword(resetToken, newPassword);
    }

}


