/*
 * This Java file contains the declaration
 * of the HashMap<KeyType, DataType> class which
 * contains all the methods of the class
 * file HashMap.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     26 february 2023
 * Modified 15 february 2023
 */

import java.util.*;

public class HashMap<KeyType, DataType>  {

    private static final int DEFAULT_CAPACITY = 23;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int size = 0;
    private int capacity;
    private final float loadFactor;

    public HashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR); }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0)
            this.capacity = DEFAULT_CAPACITY;
        else
            capacity = nextPrime(initialCapacity);
        this.loadFactor = loadFactor;
        this.map = new Node[this.capacity];
    }

    private int hash(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return Math.abs(keyHash);
    }

    private boolean needRehash() {
        return size > capacity * loadFactor;
    }

    public int size() {
        return size;
    }

    public int capacity(){
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    private int nextPrime(int number) {
        if (number % 2 == 0)
            number++;
        while (!isPrime(number))
            number += 2;
        return number;
    }

    /** Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Increases capacity to the next prime number after capacity * CAPACITY_INCREASE_FACTOR and
     * reassigns all contained values
     */
    private void rehash() {
        if (needRehash()) {
            Node<KeyType, DataType>[] oldMap = map;
            capacity = nextPrime(capacity * CAPACITY_INCREASE_FACTOR);
            clear();
            for (Node<KeyType, DataType> node : oldMap) {
                while (node != null) {
                    put(node.key, node.data);
                    node = node.next;
                }
            }
        }
    }

    /** Average Case : O(1)
     * Finds if the key is already assigned
     * @param key Key which we want to know if exists already
     * @return if key is already used
     */
    public boolean containsKey(KeyType key) {
        return get(key) != null;
    }

    /**  Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Finds if the value is already present
     * @param value Value which we want to know if exists already
     * @return if value is already present
     */
    public boolean containsValue(DataType value) {
        for (Node<KeyType, DataType> node: map) {
            while (node != null) {
                if (value.equals(node.data))
                    return true;
                node = node.next;
            }
        }
        return false;
    }

    /** Average Case : O(1)
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        Node<KeyType, DataType> node = map[hash(key)];
        while (node != null) {
            if (key.equals(node.key))
                return node.data;
            node = node.next;
        }
        return null;
    }

    /** Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        Node<KeyType, DataType> node = map[hash(key)];
        DataType old = get(key);
        while (node != null) {
            if (key.equals(node.key)) {
                node.data = value;
                rehash();
                return old;
            }
            else if (node.next == null){
                node.next = new Node(key, value);
                size++;
                rehash();
                return old;
            }
            node = node.next;
        }
        map[hash(key)] = new Node(key, value);
        size++;
        rehash();
        return old;
    }

    /** Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key if it's absent
     * @param key Key which will have its value assigned if absent
     * @return Current DataType instance at key (null if absent)
     */
    public DataType putIfAbsent(KeyType key, DataType value) {
        DataType val = get(key);
        if (!containsKey(key))
            val = put(key, value);
        return val;
    }

    /** Average Case : O(1)
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        Node<KeyType, DataType> current  = map[hash(key)];
        Node<KeyType, DataType> previous = null;
        while(current != null) {
            if (key.equals(current.key) && current.next == null && current == map[hash(key)]) {
                map[hash(key)] = null;
                size--;
                return current.data;
            }
            else if (key.equals(current.key) && current.next != null && current == map[hash(key)]) {
                map[hash(key)] = current.next;
                size--;
                return current.data;
            }
            else if (key.equals(current.key) && current != map[hash(key)]) {
                assert previous != null;
                previous.next = current.next;
                size--;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    /** Worst Case : O(1)
     * Removes all nodes
     */
    public void clear() {
        this.map = new Node[capacity];
        size = 0;
    }

    static class Node<KeyType, DataType> implements Iterable<Node<KeyType, DataType>> {
        final KeyType key;
        DataType data;
        Node<KeyType, DataType> next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }

        @Override
        public Iterator<Node<KeyType, DataType>> iterator() {
            return new NodeIterator(this);
        }
        private class NodeIterator implements Iterator<Node<KeyType, DataType>> {
            private Node<KeyType, DataType> curr;

            NodeIterator(Node<KeyType, DataType> node) {
                curr = node;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Node<KeyType, DataType> next() {
                Node<KeyType, DataType> old = curr;
                if (!hasNext())
                    throw new NoSuchElementException();
                else
                    curr = curr.next;
                return old;
            }
        }
    }
}