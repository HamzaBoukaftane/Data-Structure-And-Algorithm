/*
 * This Java file contains the declaration
 * of the AvlTree<T extends Comparable<T>>
 * class which contains all the methods of
 * the class AvlTree<T extends Comparable<T>>
 * file AvlTree.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 march 2023
 * Modified 17 march 2023
 */

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    @Override
    public void add(T value) {
        this.root = add(value, this.root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        return balance(super.add(value,curNode));
    }

    @Override
    public void remove(T value) {
        this.root = remove(value, this.root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        return balance(super.remove(value,curNode));
    }

    protected BinaryNode<T> balance(BinaryNode<T> curNode) {
        if (curNode == null)
            return null;
        int heightDiff = balanceFactor(curNode);
        if (heightDiff > 1)
        {
            curNode = getHeight(curNode.right.right) >= getHeight(curNode.right.left) ?
            rotateRight(curNode) : doubleRotateRight(curNode);
        }
        else if (heightDiff< -1)
        {
            curNode = getHeight(curNode.left.left) >= getHeight(curNode.left.right) ?
            rotateLeft(curNode) : doubleRotateLeft(curNode);
        }
        updateHeight(curNode);
        return curNode;
    }

    protected BinaryNode<T> rotateRight(BinaryNode<T> curNode) {
        BinaryNode<T> k2 = curNode.right;
        curNode.right = k2.left;
        k2.left = curNode;
        curNode.height = Math.max(getHeight(curNode.left), getHeight(curNode.right)) + 1;
        k2.height = Math.max(getHeight(k2.right),curNode.height) + 1;
        return k2;
    }

    protected BinaryNode<T> doubleRotateRight(BinaryNode<T> curNode) {
        curNode.right = rotateLeft(curNode.right);
        return rotateRight(curNode);
    }

    protected BinaryNode<T> rotateLeft(BinaryNode<T> curNode){
        BinaryNode<T> k1 = curNode.left;
        curNode.left = k1.right;
        k1.right = curNode;
        curNode.height = Math.max(getHeight(curNode.left), getHeight(curNode.right)) + 1;
        k1.height = Math.max(getHeight(k1.left), curNode.height) + 1;
        return k1;
    }

    protected BinaryNode<T> doubleRotateLeft(BinaryNode<T> curNode) {
        curNode.left = rotateRight(curNode.left);
        return rotateLeft(curNode);
    }

    private int balanceFactor(BinaryNode<T> node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private int getHeight(BinaryNode<T> node) {
        if (node == null) return 0;
        return node.height;
    }

    private void updateHeight(BinaryNode<T> node) {
        int leftChildHeight = getHeight(node.left);
        int rightChildHeight = getHeight(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }
}
