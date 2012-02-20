package com.kelt.disk.db.web;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Long id;
    private String name;
    
    private List<TreeNode> childs = new ArrayList<TreeNode>();
    
    public TreeNode(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public List<TreeNode> getChildNodes() {
        return childs;
    }
    
    public TreeNode addChildNode(TreeNode node) {
        childs.add(node);
        return node;
    }
    
    public TreeNode addChildNode(Long id, String name) {
        TreeNode node = new TreeNode(id, name);
        childs.add(node);
        return node;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
}
