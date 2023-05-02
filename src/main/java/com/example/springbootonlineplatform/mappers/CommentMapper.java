package com.example.springbootonlineplatform.mappers;

import com.example.springbootonlineplatform.dtos.CommentDto;
import com.example.springbootonlineplatform.models.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment map(CommentDto dto);
    CommentDto map(Comment entity);
    List<CommentDto> map(List<Comment> entity);
}
