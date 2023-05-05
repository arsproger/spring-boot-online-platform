package com.it.academy.mappers;

import com.it.academy.dtos.CommentDto;
import com.it.academy.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "userId", target = "user.id")
    Comment map(CommentDto dto);

    @Mapping(source = "user.id", target = "userId")
    CommentDto map(Comment entity);

    List<CommentDto> map(List<Comment> entity);
}
