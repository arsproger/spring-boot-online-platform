package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.UserDTO;
import com.example.springbootonlineplatform.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convertToListDTO(List<User> users) {
        return users.stream().map(this::convertToDTO).toList();
    }
}
