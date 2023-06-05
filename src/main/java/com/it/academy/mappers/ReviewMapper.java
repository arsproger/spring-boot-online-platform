package com.it.academy.mappers;

import com.it.academy.dto.ReviewDto;
import com.it.academy.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "userId", target = "user.id")
    Review map(ReviewDto dto);

    @Mapping(source = "user.id", target = "userId")
    ReviewDto map(Review entity);

    List<ReviewDto> map(List<Review> entity);
}
