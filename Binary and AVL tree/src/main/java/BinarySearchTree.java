/*
 * This Java file contains the declaration
 * of the BinarySearchTree<T extends Comparable<T>>
 * class which contains all the methods of
 * the class BinarySearchTree<T extends Comparable<T>>
 * file BinarySearchTree.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 march 2023
 * Modified 17 march 2023
 */

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T> {
    @Override
    public void add(T data) {this.root = add(data, root);}

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        if (curNode == null)
            return new BinaryNode<T>(value);
        int valueDifference = value.compareTo(curNode.value);
        if (valueDifference > 0)
            curNode.right = add(value, curNode.right);
        else if (valueDifference < 0)
            curNode.left = add(value, curNode.left);
        else
            throw new RuntimeException("Cette valeur est deja dans l'arbre ");
        return curNode;
    }

    @Override
    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, BinaryNode<T> curNode) {
        if (curNode == null)
            return false;
        else if (curNode.value == value)
            return true;
        int valueDifference = value.compareTo(curNode.value);
        if (valueDifference > 0)
            return contains(value, curNode.right);
        else
            return contains(value, curNode.left);
    }

    @Override
    public void remove(T value) {
        this.root = remove(value, root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        if (curNode == null)
            return null;
        int valueDifference = value.compareTo(curNode.value);
        if (valueDifference > 0)
            curNode.right = remove(value, curNode.right);
        else if (valueDifference < 0)
            curNode.left = remove(value, curNode.left);
        else if (curNode.right != null) {
            curNode.value = findMin(curNode.right).value;
            curNode.right = remove(curNode.value, curNode.right);
        }
        else
            curNode = (curNode.left != null) ? curNode.left : curNode.right;
        return curNode;
    }

    protected BinaryNode<T> findMin(BinaryNode<T> curNode) {
        if (curNode == null)
            return null;
        else if (curNode.left == null)
            return curNode;
        return findMin(curNode.left);
    }

    protected BinaryNode<T> findMax(BinaryNode<T> curNode) {
        if (curNode == null)
            return null;
        else if (curNode.right == null)
            return curNode;
        return findMax(curNode.right);
    }
}
