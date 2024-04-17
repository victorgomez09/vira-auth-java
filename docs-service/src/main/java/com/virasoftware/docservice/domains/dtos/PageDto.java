package com.virasoftware.docservice.domains.dtos;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.virasoftware.common.dto.Dto;
import com.virasoftware.docservice.domains.entities.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageDto implements Dto<Page> {
    private String id;
    private String name;
    private String body;
    private String parent;
    private String owner;
    private String treePos;
    private List<PageDto> subPages;
    private Instant creationDate;
    private Instant modificationDate;
    private String space;

    @Override
    public PageDto fromEntity(Page page) {
    	PageDto userDto = new PageDto();
    	BeanUtils.copyProperties(page, userDto);
    	
    	return userDto;
    }
    
    @Override
    public Page toEntity() {
        Page page = new Page();
        BeanUtils.copyProperties(this, page);
        
        return page;
    }

}
