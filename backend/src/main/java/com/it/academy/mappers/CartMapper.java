package com.it.academy.mappers;

import com.it.academy.dtos.CartDto;
import com.it.academy.models.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "userId", target = "user.id")
    Cart map(CartDto dto);

    @Mapping(source = "user.id", target = "userId")
    CartDto map(Cart entity);

    List<CartDto> map(List<Cart> entities);
}

