package com.it.academy.mappers;

import com.it.academy.dtos.CategoryDto;
import com.it.academy.models.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category map(CategoryDto dto);

    CategoryDto map(Category entity);

    List<CategoryDto> map(List<Category> entities);
}
