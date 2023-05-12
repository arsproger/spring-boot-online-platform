package com.it.academy.mappers;

import com.it.academy.dto.UserDto;
import com.it.academy.models.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDto dto);

    UserDto map(User entity);

    List<UserDto> map(List<User> entities);
}
