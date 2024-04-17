package com.virasoftware.docservice.domains.dtos;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import com.virasoftware.common.dto.Dto;
import com.virasoftware.docservice.domains.entities.SpaceUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceUserDto implements Dto<SpaceUser> {
    private String id;
    private String name;
    private String code;
    private String description;
    private Instant creationDate;
    private Instant modificationDate;

    @Override
    public SpaceUserDto fromEntity(SpaceUser spaceUser) {
    	SpaceUserDto userDto = new SpaceUserDto();
    	BeanUtils.copyProperties(spaceUser, userDto);
    	
    	return userDto;
    }
    
    @Override
    public SpaceUser toEntity() {
        SpaceUser spaceUser = new SpaceUser();
        BeanUtils.copyProperties(this, spaceUser);
        
        return spaceUser;
    }

}
