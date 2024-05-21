package com.virasoftware.docservice.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceUserDto {
    private String id;
    private String user;
    private String permission;
}
