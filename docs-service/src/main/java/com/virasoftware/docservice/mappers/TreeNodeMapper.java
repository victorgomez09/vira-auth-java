package com.virasoftware.docservice.mappers;

import org.mapstruct.Mapper;

import com.virasoftware.docservice.domains.dtos.TreeNodeDto;
import com.virasoftware.docservice.tree.TreeNode;

@Mapper(componentModel = "spring")
public interface TreeNodeMapper {

    TreeNodeDto toDto(TreeNode entity);
}
