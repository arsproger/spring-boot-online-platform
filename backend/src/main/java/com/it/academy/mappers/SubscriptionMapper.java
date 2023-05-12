package com.it.academy.mappers;

import com.it.academy.dto.SubscriptionDto;
import com.it.academy.models.Subscription;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    Subscription map(SubscriptionDto dto);

    SubscriptionDto map(Subscription entity);

    List<SubscriptionDto> map(List<Subscription> entities);
}
