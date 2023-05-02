package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.UserDTO;
import com.example.springbootonlineplatform.models.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDTO dto);
    UserDTO map(User entity);
    List<UserDTO> map(List<User> entities);
}
