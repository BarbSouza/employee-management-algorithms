package org.example.model;

public class TreeNode {
    private Employee data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Employee data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
