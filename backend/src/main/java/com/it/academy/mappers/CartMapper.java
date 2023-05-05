package com.it.academy.mappers;

import com.it.academy.dtos.CartDto;
import com.it.academy.models.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapping;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart map(CartDto dto);

    CartDto map(Cart entity);

    List<CartDto> map(List<Cart> entities);
}
