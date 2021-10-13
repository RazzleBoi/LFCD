package com.company;

public class Node {

    private Node left;

    private String key;
    private Integer val;
    private Node right;

    public Node(String key, int val) {
        this.key = key;
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Integer getVal() {
        return val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
