package com.it.academy.services;

import com.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    Long create(User user);

    Long save(User user);

    Long block(Long id);

    Long unlock(Long id);

    void updateById(Long id, User updatedUser);

    Optional<User> getByEmail(String email);

    Optional<User> getByResetToken(String resetToken);

    void processOAuthPostLogin(String username, String name, String registrationId);

    List<User> getUserByCourseId(Long courseId);

    Integer getCountOfAllUsers();

    Integer getCountOfAllUsersToday();

    Boolean coursePurchaseCheck(Long userId, Long courseId);

}
