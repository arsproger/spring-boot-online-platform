package com.it.academy.security;

import com.it.academy.models.User;
import com.it.academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailsUserService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public DetailsUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getByEmail(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден!");

        return new DetailsUser(user.get());
    }

}
