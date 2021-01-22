package com.tree;

/*
    Depth First Traversals:
    (a) Inorder (Left, Root, Right)
    (b) Preorder (Root, Left, Right)
    (c) Postorder (Left, Right, Root)
 */
public class TreeTraversal {

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    static void printPostorder(BinaryTreeNode node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.data + " ");
    }

    /* Given a binary tree, print its nodes in inorder*/
    static void printInorder(BinaryTreeNode node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /* Given a binary tree, print its nodes in preorder*/
    static void printPreorder(BinaryTreeNode node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.data + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }

    public static void main(String[] args)
    {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.left = new BinaryTreeNode(2);
        tree.right = new BinaryTreeNode(3);
        tree.left.left = new BinaryTreeNode(4);
        tree.left.right = new BinaryTreeNode(5);

        System.out.println("Preorder traversal of binary tree is ");
        printPreorder(tree);

        System.out.println("\nInorder traversal of binary tree is ");
        printInorder(tree);

        System.out.println("\nPostorder traversal of binary tree is ");
        printPostorder(tree);
    }

}
