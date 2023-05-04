package com.it.academy.services;

import com.it.academy.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    Long save(User user);

    Long deleteById(Long id);

    Long updateById(Long id, User updatedUser);

}
