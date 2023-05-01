package com.it.academy.services;

import com.it.academy.models.User;
import com.it.academy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public Long updateById(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;

        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        return userRepository.save(user).getId();
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
