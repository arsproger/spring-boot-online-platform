package com.it.academy.mappers;

import com.it.academy.dto.VideoDto;
import com.it.academy.models.Video;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface VideoMapper {
    Video map(VideoDto dto);

    VideoDto map(Video entity);

    List<VideoDto> map(List<Video> entities);
}
