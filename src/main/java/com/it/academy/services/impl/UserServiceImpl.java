package com.it.academy.services.impl;

import com.it.academy.dao.UserDao;
import com.it.academy.entities.Cart;
import com.it.academy.entities.User;
import com.it.academy.enums.Provider;
import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.CartService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final UserDao userDao;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new AppException("User not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Long create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(Role.ROLE_STUDENT);
        user.setProvider(Provider.LOCAL);
        user.setCart(cartService.save(new Cart()));
        user.setCreatedDate(LocalDate.now());
        if (user.getImageUrl() == null) user.setImageUrl("user-default.png");

        return userRepository.save(user).getId();
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public Long deleteById(Long id) {
        User user = getById(id);
        user.setStatus(UserStatus.DELETED);
        return userRepository.save(user).getId();
    }

    @Override
    public void updateById(Long id, User updatedUser) {
        User user = getById(id);

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setDateOfBirth(updatedUser.getDateOfBirth());

        userDao.updateUserById(id, user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public void processOAuthPostLogin(String username, String name, String registrationId) {
        if (userRepository.findByEmail(username).isEmpty()) {
            User user = User.builder()
                    .role(Role.ROLE_STUDENT)
                    .provider(registrationId.equals("google")
                            ? Provider.GOOGLE
                            : Provider.GITHUB)
                    .fullName(name)
                    .email(username)
                    .status(UserStatus.ACTIVE)
                    .imageUrl("user-default.png")
                    .createdDate(LocalDate.now())
                    .cart(cartService.save(new Cart()))
                    .build();

            userRepository.save(user);
        }
    }

    @Override
    public List<User> getUserByCourseId(Long courseId) {
        return userDao.getUserByCourseId(courseId);
    }

    @Override
    public Integer getCountOfAllUsers() {
        return userDao.getCountOfAllUsers();
    }

    @Override
    public Integer getCountOfAllUsersToday() {
        return userDao.getCountOfAllUsersToday();
    }

}
