/*
Name:       Connor Heard
Date:       10/25/2023
Course:     CSC 220 - Data Structures
*/

public class Heap extends AbstractHeap {
    /**
     * Default constructor - initializes data to have a size equal to MAX_SIZE
     */
    public Heap() {
        data = new int[MAX_SIZE];

        // keep track of number of elements
        int size = 0;
    }
    
    // Methods for getting the index of a node's left child, right child, or parent
    protected int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    protected int getRightIndex(int index) {
        return 2 * index + 2;
    }

    protected int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    
    // Methods for getting the data stored at some index in the heap
    protected int getLeftData(int index) {
        return data[getLeftIndex(index)];    
    }

    protected int getRightData(int index) {
        return data[getRightIndex(index)];
    }

    protected int getParentData(int index) {
        return data[getParentIndex(index)];
    }


    // Methods for checking if a node has a left child, right child, or parent
    protected boolean hasLeft(int index) {
        return getLeftIndex(index) < size;
    }

    protected boolean hasRight(int index) {
        return getRightIndex(index) < size;
    }

    protected boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    // Checks if the heap is empty/full
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    /**
     * Returns the String representation of the heap
     * @return          the heap as a String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += data[i] + " ";
        }
        return s.trim();
    }
    
    /**
     * Swap the data stored at two different indices in the heap
     * @param           index0 - the index whose data will be swapped with the data at index1
     * @param           index1 - the index whose data will be swapped with the data at index0
     */
    public void swap(int index0, int index1) {
        // make sure our indices are within range of maximum size
        if (index0 <= 9 && index0 >= 0 && index1 <= 9 && index1 >= 0) {
            int temp = data[index0];
            data[index0] = data[index1];
            data[index1] = temp;
        }
    }
    
    /**
     * Adds a new node to the heap
     * @param           element - the data to add to the heap
     */
    public void add(int element) {

        // if the heap is not full, then we add specified element at the end and increment the counter
        if (!isFull()) {
            data[size] = element;
            size++;

            // maintain the heap properties by moving the new node up the heap until
            // it reaches the correct index
            maxHeapifyUp();

        }
    }

    /**
     * Removes and returns the data stored at the root (index 0)
     * @return          the data stored at the root
     */
    public int getMax() {

        // if the heap is not empty, then we swap max element with the last node in the heap
        if (!isEmpty()) {
            int root = data[0];
            data[0] = data[size-1];
            size--;

            // maintain the heap properties by moving the new root node to its correct index
            maxHeapifyDown();

            // return the max element from the heap that we removed
            return root;
        }

        return -1;
        
    }
    
    /**
     * Restores the heap property by repeatedly swapping the last leaf node up the heap until properly positioned
     */
    public void maxHeapifyUp() {
        int index = size - 1;

        // continues to check if the node has a parent and then swaps elements
        // if the child node is greater in value
        while (hasParent(index) && data[index] > getParentData(index)) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
         
          }
    }
    
    /**
     * Restores the heap property by repeatedly swapping the root node down the heap until properly positioned
     */
    public void maxHeapifyDown() {
        int root = 0;

        while (hasLeft(root)) {
            int largerChildIndex = getLeftIndex(root);

            // Check if the right child exists and if its is larger than the left child
            if (hasRight(root) && getRightData(root) > getLeftData(root)) {
                largerChildIndex = getRightIndex(root);
            }

            // Compare the larger child node with the current node
            if (data[root] >= data[largerChildIndex]) {
                break;
            }

            else {
                // swap current node with the larger child node
                swap(root, largerChildIndex);
                root = largerChildIndex;
            }
        }

    }
}
