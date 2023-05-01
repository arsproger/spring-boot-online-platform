package com.example.springbootonlineplatform.security;

import com.example.springbootonlineplatform.models.User;
import com.example.springbootonlineplatform.services.UserService;
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
