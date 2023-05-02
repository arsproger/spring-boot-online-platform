package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.ArticleDto;
import com.example.springbootonlineplatform.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article map(ArticleDto dto);
    ArticleDto map(Article entity);
    List<ArticleDto> map(List<Article> entities);
}
