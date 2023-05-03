package com.it.academy.mappers;

import com.it.academy.dtos.ArticleDto;
import com.it.academy.models.Article;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article map(ArticleDto dto);

    ArticleDto map(Article entity);

    List<ArticleDto> map(List<Article> entities);
}
