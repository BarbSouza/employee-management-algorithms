package org.example.model;

public class EmployeeBinaryTree {
    private TreeNode root;

    public EmployeeBinaryTree() {
        this.root = null;
    }

    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.getLeft() == null) {
                current.setLeft(newNode);
                return;
            } else {
                queue.add(current.getLeft());
            }

            if (current.getRight() == null) {
                current.setRight(newNode);
                return;
            } else {
                queue.add(current.getRight());
            }
        }
    }

    public void levelOrderTraversal(org.example.view.ConsoleView view) {
        if (root == null) {
            view.displayMessage("The tree is empty.");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int nodeNumber = 1;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            view.displayMessage("Node " + nodeNumber + ": ");
            view.displayEmployeeDetails(current.getData());
            nodeNumber++;

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public int getHeight() {
        return getHeightRec(root);
    }
    private int getHeightRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeightRec(root.getLeft());
        int rightHeight = getHeightRec(root.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int getNodeCount() {
        return countNodesRec(root);
    }
    private int countNodesRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.getLeft()) + countNodesRec(root.getRight());
    }


}
