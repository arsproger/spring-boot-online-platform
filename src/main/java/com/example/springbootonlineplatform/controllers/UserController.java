package com.example.springbootonlineplatform.controllers;

import com.example.springbootonlineplatform.dtos.UserDTO;
import com.example.springbootonlineplatform.mappers.UserMapper;
import com.example.springbootonlineplatform.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userMapper.map(userService.getAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userMapper.map(userService.getById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserDTO user) {
        Long id = userService.save(userMapper.map(user));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
        Long deletedId = userService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserById(@PathVariable Long id, @RequestBody UserDTO user) {
        Long updatedId = userService.updateById(id, userMapper.map(user));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
