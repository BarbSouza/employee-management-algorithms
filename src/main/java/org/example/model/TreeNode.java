package org.example.model;

/**
 * This class represents a node in a binary tree that holds an Employee object.
 */
public class TreeNode {
    private Employee data;
    private TreeNode left;
    private TreeNode right;

    // Constructor
    public TreeNode(Employee data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
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
