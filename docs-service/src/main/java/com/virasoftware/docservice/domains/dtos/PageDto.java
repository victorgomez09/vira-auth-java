package com.virasoftware.docservice.domains.dtos;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageDto {
    private String id;
    private String name;
    private String body;
    private PageDto parent;
    private String owner;
    private String treePos;
    private List<PageDto> subPages;
    private Instant creationDate;
    private Instant modificationDate;
    private SpaceDto space;
}
