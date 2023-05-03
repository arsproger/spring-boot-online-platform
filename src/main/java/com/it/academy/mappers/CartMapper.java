package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.CartDto;
import com.example.springbootonlineplatform.models.Cart;
import com.example.springbootonlineplatform.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "userId", target = "user.id")
    Cart map(CartDto dto);

    @Mapping(source = "user.id", target = "userId")
    CartDto map(Cart entity);

    List<CartDto> map(List<Cart> entities);
}

