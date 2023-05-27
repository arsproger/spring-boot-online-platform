package com.it.academy.mappers;

import com.it.academy.dto.CartDto;
import com.it.academy.entities.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart map(CartDto dto);

    CartDto map(Cart entity);

    List<CartDto> map(List<Cart> entities);
}
