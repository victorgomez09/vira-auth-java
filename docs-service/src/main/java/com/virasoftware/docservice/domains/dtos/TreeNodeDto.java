package com.virasoftware.docservice.domains.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TreeNodeDto {
    private PageDto page;
    private List<TreeNodeDto> childNodes;
}
