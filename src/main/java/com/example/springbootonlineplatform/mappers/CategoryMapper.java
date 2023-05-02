package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.CategoryDto;
import com.example.springbootonlineplatform.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category map(CategoryDto dto);
    CategoryDto map(Category entity);
    List<CategoryDto> map(List<Category> entities);
}
