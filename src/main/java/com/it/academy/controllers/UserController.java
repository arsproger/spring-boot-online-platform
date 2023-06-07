package com.it.academy.controllers;

import com.it.academy.dto.UserDto;
import com.it.academy.dto.UserUpdateDto;
import com.it.academy.mappers.UserMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "Контроллер пользователя")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = mapper.map(userService.getAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = mapper.map(userService.getById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    @Operation(summary = "Получение текущего авторизованного пользователя")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal DetailsUser detailsUser) {
        UserDto user = mapper.map(userService.getById(detailsUser.getUser().getId()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/block/{id}")
    public ResponseEntity<Long> blockUserById(@PathVariable Long id) {
        Long deletedId = userService.block(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/unlock/{id}")
    public ResponseEntity<Long> unlockUserById(@PathVariable Long id) {
        Long deletedId = userService.unlock(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public HttpStatus updateUserById(@AuthenticationPrincipal DetailsUser detailsUser,
                                     @RequestBody @Valid UserUpdateDto userDto) {
        userService.updateById(detailsUser.getUser().getId(), mapper.map(userDto));
        return HttpStatus.OK;
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Получение всех пользователей курса",
            description = "При получении всех пользователей определенного курса, нужно передать id курса")
    public ResponseEntity<List<UserDto>> getByCourseId(@PathVariable Long courseId) {
        List<UserDto> users = mapper.map(userService.getUserByCourseId(courseId));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/count")
    @Operation(summary = "Получение количества всех пользователей")
    public ResponseEntity<Integer> getCountOfAllUsers() {
        Integer userCount = userService.getCountOfAllUsers();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

    @GetMapping("/count/today")
    @Operation(summary = "Получение количества всех пользователей зарегистрированных сегодня")
    public ResponseEntity<Integer> getCountOfAllUsersToday() {
        Integer userCount = userService.getCountOfAllUsersToday();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }

}
