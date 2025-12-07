package org.example.model;

/**
 * This class represents a binary tree structure to store Employee objects.
 */
public class EmployeeBinaryTree {
    private TreeNode root;

    public EmployeeBinaryTree() {
        this.root = null;
    }

    // Insert an employee into the binary tree using level-order insertion
    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        // Level-order traversal to find the first available position
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

    // Level-order traversal to display employee details
    public void levelOrderTraversal(org.example.view.ConsoleView view) {
        if (root == null) {
            view.displayMessage("The tree is empty.");
            return;
        }
        // Use a queue for level-order traversal
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
    // Get the height of the binary tree
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

    // Get the total number of nodes in the binary tree
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
