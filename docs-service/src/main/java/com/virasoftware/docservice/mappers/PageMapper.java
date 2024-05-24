package com.virasoftware.docservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.virasoftware.docservice.domains.dtos.PageDto;
import com.virasoftware.docservice.domains.entities.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {

	@Mapping(target = "space.users", ignore = true)
	PageDto toDto(Page entity);

	@Mapping(target = "space.users", ignore = true)
	Page toEntity(PageDto dto);
}
