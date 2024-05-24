// package com.virasoftware.docservice.trie;

// import java.util.HashMap;

// import com.virasoftware.docservice.domains.entities.Page;

// public class Trie {
//     private TrieNode root;

//     public Trie() {
//         root = new TrieNode();
//     }

//     public void insert(String treePos) {
//         HashMap<Page, TrieNode> children = root.getChildren();
//         for (int i = 0; i < treePos.length(); i++) {
//             char c = treePos.charAt(i);
//             TrieNode node;
//             if (children.containsKey(c)) {
//                 node = children.get(c);
//             } else {
//                 node = new TrieNode(c);
//                 children.put(c, node);
//             }
//             children = node.getChildren();

//             if (i == treePos.length() - 1) {
//                 node.setLeaf(true);
//             }
//         }
//     }

//     public boolean search(String word) {
//         HashMap<Character, TrieNode> children = root.getChildren();

//         TrieNode node = null;
//         for (int i = 0; i < word.length(); i++) {
//             char c = word.charAt(i);
//             if (children.containsKey(c)) {
//                 node = children.get(c);
//                 children = node.getChildren();
//             } else {
//                 node = null;
//                 break;
//             }
//         }

//         if (node != null && node.isLeaf()) {
//             return true;
//         } else {
//             return false;
//         }
//     }
// }
