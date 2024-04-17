package com.virasoftware.docservice.domains.dtos;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import com.virasoftware.common.dto.Dto;
import com.virasoftware.docservice.domains.entities.Space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceDto implements Dto<Space> {
    private String id;
    private String name;
    private String code;
    private String description;
    private Instant creationDate;
    private Instant modificationDate;

    @Override
    public SpaceDto fromEntity(Space space) {
    	SpaceDto userDto = new SpaceDto();
    	BeanUtils.copyProperties(space, userDto);
    	
    	return userDto;
    }
    
    @Override
    public Space toEntity() {
        Space space = new Space();
        BeanUtils.copyProperties(this, space);
        
        return space;
    }

}
