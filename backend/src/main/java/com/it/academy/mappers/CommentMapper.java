package com.it.academy.mappers;

import com.it.academy.dto.CommentDto;
import com.it.academy.models.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment map(CommentDto dto);

    CommentDto map(Comment entity);

    List<CommentDto> map(List<Comment> entity);
}
