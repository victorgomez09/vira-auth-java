package com.virasoftware.docservice.tree;

import java.util.LinkedList;
import java.util.List;

import com.virasoftware.docservice.domains.entities.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TreeNode {
    private final String key;
    private final String label;
    private final String data;
    private TreeNode parent;
    private boolean leaf = true;
    private List<TreeNode> children = new LinkedList<>();

    public void addChild(TreeNode childNode) {
        this.children.add(childNode);
    }

}
