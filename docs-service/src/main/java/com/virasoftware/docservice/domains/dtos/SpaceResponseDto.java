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
public class SpaceResponseDto {
    private String id;
    private String name;
    private String code;
    private List<UserDto> users;
    private String owner;
    private String description;
    private Instant creationDate;
    private Instant modificationDate;
    
}