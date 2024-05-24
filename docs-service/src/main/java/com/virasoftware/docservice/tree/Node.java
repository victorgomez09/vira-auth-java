package com.virasoftware.docservice.tree;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Node {
    private String key;
    private String label;
    private String data;
    private boolean leaf = true;
    private List<Node> children;
}
