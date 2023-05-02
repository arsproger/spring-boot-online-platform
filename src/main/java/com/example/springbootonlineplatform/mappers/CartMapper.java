package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.CartDto;
import com.example.springbootonlineplatform.models.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart map(CartDto dto);
    CartDto map(Cart entity);
    List<CartDto> map(List<Cart> entities);
}
