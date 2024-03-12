/*
 * This Java file contains the declaration
 * of the Heap class which contains all the
 * methods of the class Heap.
 * file Heap.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 March 2023
 * Modified 2  April 2023
 */

import java.util.*;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    private static final boolean DEFAULT_IS_MIN_HEAP = true;

    private final ArrayList<ValueType> data;

    private final boolean isMinHeap;

    public Heap() {
        this(DEFAULT_IS_MIN_HEAP);
    }

    public Heap(Collection<ValueType> data) {
        this(DEFAULT_IS_MIN_HEAP, data);
    }

    public Heap(boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        data = new ArrayList<>();
    }

    public Heap(boolean isMinHeap, Collection<ValueType> data) {
        this.isMinHeap = isMinHeap;
        this.data = new ArrayList<>(data);
        heapify();
    }

    public int size() {
        return data.size();
    }

    public int lastChildIndex() { return data.size() - 1; }

    public ValueType peek() {
        return data.get(0);
    }

    // Worst Case O(1)
    private int parent(int childIndex) { return (childIndex - 1)/2; }

    // Worst Case O(1)
    private int left(int parentIndex) { return (2 * parentIndex) + 1; }

    // Worst Case O(1)
    private int right(int parentIndex) { return (2 * parentIndex) + 2; }

    // Worst Case O(1)
    private void swap(int firstIndex, int secondIndex) {
        ValueType firstIndexValue = this.data.get(firstIndex);
        ValueType secondIndexValue = this.data.get(secondIndex);
        this.data.set(firstIndex, secondIndexValue);
        this.data.set(secondIndex,firstIndexValue);
    }

    // Worst Case O(n)
    private void heapify() {
        for (int index = size()/2; index >= 0; index--)
            percolateDown(index);
    }

    // Worst Case O(log n)
    private void percolateUp(int childIndex) {
        if (childIndex != 0) {
            percolateUpOnce(childIndex);
            percolateUp(parent(childIndex));
        }
    }

    private void percolateUpOnce(int childIndex) {
        int parentIndex = parent(childIndex);
        ValueType childVal = data.get(childIndex);
        ValueType parentVal = data.get(parentIndex);
        if (parentVal.compareTo(childVal) > 0 && isMinHeap)
            swap(parentIndex, childIndex);
        else if (parentVal.compareTo(childVal) < 0 && !isMinHeap)
            swap(parentIndex, childIndex);
    }

    // Worst Case O(log n)
    private void percolateDown(int parentIndex) {
        percolateDown(parentIndex, size());
    }

    private void percolateDown(int parentIndex, int size) {
        if (parentIndex < size) {
            int nextParentIndex = percolateDownOnce(parentIndex, size);
            percolateDown(nextParentIndex,size);
        }
    }

    private int percolateDownOnce(int parentIndex, int size) {
        int childIndex = extremeChild(parentIndex, size);
        if (childIndex < size) {
            ValueType parentVal = data.get(parentIndex);
            ValueType childVal = data.get(childIndex);
            if (parentVal.compareTo(childVal) > 0 && isMinHeap)
                swap(parentIndex, childIndex);
            else if (parentVal.compareTo(childVal) < 0 && !isMinHeap)
                swap(parentIndex, childIndex);
        }
        return childIndex;
    }

    private int extremeChild(int parentIndex, int size) {
        int leftIndex = left(parentIndex);
        int rightIndex = right(parentIndex);
        if (rightIndex >= size && leftIndex < size)
            return leftIndex;
        else if (rightIndex < size && leftIndex < size) {
            ValueType leftVal = data.get(leftIndex);
            ValueType rightVal = data.get(rightIndex);
            if (isMinHeap)
                return (leftVal.compareTo(rightVal) < 0) ? leftIndex : rightIndex;
            else
                return (leftVal.compareTo(rightVal) > 0) ? leftIndex : rightIndex;
        }
        return size;
    }

    // Worst Case O(log n)
    public void push(ValueType element) {
        data.add(element);
        percolateUp(data.size() - 1);
    }

    // Worst Case O(log n)
    public ValueType pop() {
        ValueType extremeVal = peek();
        swap(0, lastChildIndex());
        data.remove(lastChildIndex());
        percolateDown(0);
        return extremeVal;
    }

    // Worst Case O(n log n)
    public ArrayList<ValueType> sort() {
        Heap<ValueType> tmpHeap = new Heap<>(isMinHeap, data);
        for (int index = lastChildIndex(); index > 0; index--) {
            tmpHeap.swap(0,index);
            tmpHeap.percolateDown(0, index);
        }
        ArrayList<ValueType> sortedArray = new ArrayList<>();
        for (int index = tmpHeap.lastChildIndex(); index >= 0; index--) {
            sortedArray.add(tmpHeap.data.get(index));
        }
        return sortedArray;
    }

    @Override
    public Iterator<ValueType> iterator() {
        return data.iterator();
    }
}
