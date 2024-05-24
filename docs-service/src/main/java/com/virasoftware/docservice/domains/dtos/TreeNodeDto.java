package com.virasoftware.docservice.domains.dtos;

import java.util.LinkedList;
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
    private String key;
    private String label;
    private String data;
    private TreeNodeDto parent;
    private boolean leaf;
    private List<TreeNodeDto> children;
}
