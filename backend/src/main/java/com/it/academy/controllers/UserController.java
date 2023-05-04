package com.it.academy.controllers;

import com.it.academy.dao.UserDao;
import com.it.academy.dtos.UserDto;
import com.it.academy.mappers.UserMapper;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
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

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDto user) {
        Long id = userService.save(mapper.map(user));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
        Long deletedId = userService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserById(@PathVariable Long id, @RequestBody UserDto user) {
        Long updatedId = userService.updateById(id, mapper.map(user));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<List<UserDto>> getByCourseId(@PathVariable("id") Long courseId) {
        List<UserDto> users = mapper.map(userDao.getUserByCourseId(courseId));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
