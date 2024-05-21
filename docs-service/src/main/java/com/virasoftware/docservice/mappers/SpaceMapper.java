package com.virasoftware.docservice.mappers;

import org.mapstruct.Mapper;

import com.virasoftware.docservice.domains.dtos.SpaceDto;
import com.virasoftware.docservice.domains.entities.Space;

@Mapper(componentModel = "spring")
public interface SpaceMapper {

	SpaceDto toDto(Space entity);

	Space toEntity(SpaceDto dto);
}
