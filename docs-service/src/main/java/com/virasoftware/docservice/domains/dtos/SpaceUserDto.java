package com.virasoftware.docservice.domains.dtos;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceUserDto {
    private String id;
    private String name;
    private String code;
    private String description;
    private Instant creationDate;
    private Instant modificationDate;
}
