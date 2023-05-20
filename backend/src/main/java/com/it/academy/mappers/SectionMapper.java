package com.it.academy.mappers;

import com.it.academy.dto.SectionDto;
import com.it.academy.models.Section;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    Section map(SectionDto dto);

    SectionDto map(Section entity);

    List<SectionDto> map(List<Section> entity);
}
