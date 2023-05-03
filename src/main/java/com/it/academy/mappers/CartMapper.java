package com.it.academy.mappers;

import com.it.academy.dtos.CartDto;
import com.it.academy.models.Cart;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart map(CartDto dto);
    CartDto map(Cart entity);
    List<CartDto> map(List<Cart> entities);
}
