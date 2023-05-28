package com.it.academy.controllers;

import com.it.academy.dao.UserDao;
import com.it.academy.dto.UserDto;
import com.it.academy.mappers.UserMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Контроллер пользователя")
public class UserController {
    private final UserService userService;
    private final UserDao userDao;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = mapper.map(userService.getAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = mapper.map(userService.getById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/current")
    @Operation(summary = "Получение текущего авторизованного пользователя")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal DetailsUser detailsUser) {
        UserDto user = mapper.map(userService.getById(detailsUser.getUser().getId()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
        Long deletedId = userService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserById(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        Long updatedId = userService.updateById(id, mapper.map(user));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Получение всех пользователей курса",
            description = "При получении всех пользователей определенного курса, нужно передать id курса")
    public ResponseEntity<List<UserDto>> getByCourseId(@PathVariable Long courseId) {
        List<UserDto> users = mapper.map(userDao.getUserByCourseId(courseId));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
