package com.it.academy.mappers;

import com.it.academy.dto.SectionDto;
import com.it.academy.entities.Section;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SectionMapper {
    Section map(SectionDto dto);

    SectionDto map(Section entity);

    List<SectionDto> map(List<Section> entity);
}
