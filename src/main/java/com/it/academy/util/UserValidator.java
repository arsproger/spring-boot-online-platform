package com.it.academy.util;

import com.it.academy.entities.User;
import com.it.academy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.getByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email", "", "Email уже зарегистрирован!");
    }

}
