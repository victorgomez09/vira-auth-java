package com.virasoftware.docservice.trie;

import java.util.LinkedList;
import java.util.List;

import com.virasoftware.docservice.domains.entities.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class TreeNode {
    private Page page;
    private List<TreeNode> childNodes;

    public TreeNode(Page value) {
        this.page = value;
        this.childNodes = new LinkedList<>();
    }

    public void addChild(TreeNode childNode) {
        this.childNodes.add(childNode);
    }

}
