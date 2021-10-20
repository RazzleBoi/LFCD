package com.company;

import java.util.Stack;

public class SymbolTable {
    private Node root;
    private Integer id = 0;

    public SymbolTable(Node root) {
        this.root = root;
    }

    public Integer insert(String key){
        id++;
        Node node=new Node(key, id);
        if(root==null) {
            root = node;
            return id;
        }
        Node prev=null;
        Node temp=root;
        while (temp!=null){
            if(temp.getKey().compareTo(key) == 0){
                id--;
                return temp.getVal();
            }
            if(temp.getKey().compareTo(key) > 0){
                prev=temp;
                temp=temp.getLeft();
            }
            else if (temp.getKey().compareTo(key) < 0){
                prev=temp;
                temp=temp.getRight();
            }
        }
        if(prev.getKey().compareTo(key) > 0)
            prev.setLeft(node);
        else prev.setRight(node);
        return id;
    }

    public Node search(Node root, String key)
    {
        if (root==null || root.getKey().equals(key))
            return root;
        if (root.getKey().compareTo(key) > 0)
            return search(root.getRight(), key);

        return search(root.getLeft(), key);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
