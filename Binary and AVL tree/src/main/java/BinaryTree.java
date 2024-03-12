/*
 * This Java file contains the declaration
 * of the BinaryTree<T>class which contains
 * all the methods of the class BinaryTree<T>
 * file BinaryTree.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 march 2023
 * Modified 17 march 2023
 */

public class BinaryTree<T> {
    protected BinaryNode<T> root = null;

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(BinaryNode<T> node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node.getValue() + " ");
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(BinaryNode<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BinaryNode<T> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.getValue() + " ");
            printInOrder(node.right);
        }
    }
}

