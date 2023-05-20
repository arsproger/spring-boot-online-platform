package com.it.academy.mappers;

import com.it.academy.dto.CategoryDto;
import com.it.academy.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category map(CategoryDto dto);

    CategoryDto map(Category entity);

    List<CategoryDto> map(List<Category> entities);
}
