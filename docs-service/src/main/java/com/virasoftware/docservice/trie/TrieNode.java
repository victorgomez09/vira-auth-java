package com.virasoftware.docservice.trie;

import java.util.HashMap;

import com.virasoftware.docservice.domains.entities.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TrieNode {
    private char c;
    private HashMap<Page, TrieNode> children = new HashMap<>();
    private boolean isLeaf;

    public TrieNode(char c) {
        this.c = c;
    }
}