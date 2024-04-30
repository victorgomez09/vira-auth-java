package com.virasoftware.docservice.mappers;

import org.mapstruct.Mapper;

import com.virasoftware.docservice.domains.dtos.PageDto;
import com.virasoftware.docservice.domains.entities.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {

	PageDto toDto (Page entity);
	
	Page toEntity(PageDto dto);
}
