package com.it.academy.controllers;

import com.it.academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {
    private final UserService userService;

    @Autowired
    public PasswordResetController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
        return userService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public ResponseEntity<?> saveNewPassword(@PathVariable("resetToken") String resetToken, @RequestParam String newPassword) {
        return userService.saveNewPassword(resetToken, newPassword);
    }

}


