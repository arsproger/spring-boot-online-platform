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
    @Mapping(source = "userFullname", target = "user.fullName")
    @Mapping(source = "userEmail", target = "user.email")
    @Mapping(source = "userImageUrl", target = "user.imageUrl")
    Review map(ReviewDto dto);

    @Mapping(source = "user.fullName", target = "userFullname")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "user.imageUrl", target = "userImageUrl")
    ReviewDto map(Review entity);

    List<ReviewDto> map(List<Review> entity);
}
