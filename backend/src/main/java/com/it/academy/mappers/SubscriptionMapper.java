package com.it.academy.mappers;

import com.it.academy.dtos.SubscriptionDto;
import com.it.academy.models.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(source = "courseId", target = "course.id")
    @Mapping(source = "userId", target = "user.id")
    Subscription map(SubscriptionDto dto);
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "user.id", target = "userId")
    SubscriptionDto map(Subscription entity);
    List<SubscriptionDto> map(List<Subscription> entities);
}
