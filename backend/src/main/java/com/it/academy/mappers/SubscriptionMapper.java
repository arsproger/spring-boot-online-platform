package com.it.academy.mappers;

import com.it.academy.dtos.SubscriptionDto;
import com.it.academy.models.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "courseId", target = "course.id")
    Subscription map(SubscriptionDto dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "course.id", target = "courseId")
    SubscriptionDto map(Subscription entity);

    List<SubscriptionDto> map(List<Subscription> entities);
}
