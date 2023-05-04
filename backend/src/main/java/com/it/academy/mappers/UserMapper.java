package com.it.academy.mappers;

import com.it.academy.dtos.UserDTO;
import com.it.academy.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDTO dto);
    UserDTO map(User entity);
    List<UserDTO> map(List<User> entities);
}
