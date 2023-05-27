package com.it.academy.mappers;

import com.it.academy.dto.CommentDto;
import com.it.academy.entities.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment map(CommentDto dto);

    CommentDto map(Comment entity);

    List<CommentDto> map(List<Comment> entity);
}
