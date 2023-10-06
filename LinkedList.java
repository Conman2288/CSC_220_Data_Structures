/*
Name:	 	Connor Heard
Date:		10/4/2023
Course:		CSC 220 - Data Structures
*/

public class LinkedList<Type> extends AbstractLinkedList<Type> {
	/**
	 * Default Constructor - Newly constructed lists have no nodes,
	 * so head and tail point to null.
	 */
	public LinkedList() {
		head = null;
		current = null;
		tail = null;
		numElements = 0;
	}

	/**
	 * Copy constructor - copies each element from the given linked list
	 * into the one being constructed.
	 * @param 			l - the linked list being copied from
	 */
	public LinkedList(LinkedList<Type> l) {
		head = null;
		current = null;
		tail = null;
		numElements = 0;

		// We create a temporary node object that is then
		// utilized to grab data and traverse Linkedlist l
		Node<Type> temp = l.head;
		while (temp != null) {
			add(temp.getData());
			temp = temp.getLink();
		}

	}

	/**
	 * Returns a String representation of the list ("NULL" if empty)
	 * @return 			String representation of the linked list
	 */
	public String toString() {
		// Return "NULL" if the list is empty
		if (head == null) {
			return "NULL";
		}
		else {
			// start traversing the list at the head
			Node<Type> temp = head;

			// Initialize th string representation of the linked list
			String s = "";

			while (temp != null) {
				s += temp.getData() + " ";
				temp = temp.getLink();
			}
			return s;
		}
	}

	/**
	 * Returns the number of elements in the list (not the max capacity)
	 * @return 			number of elements in the linked list
	 */
	public int size() {
		return numElements;
	}

	/**
	 * Returns true if there are no elements in the list
	 * @return 			true if list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (numElements == 0);
	}

	/**
	 * Returns true if the number of elements in the list is equal to MAX_SIZE
	 * @return 			true if list is full, false otherwise
	 */
	public boolean isFull() {
		return (numElements == MAX_SIZE);
	}

	/**
	 * Set the current Node reference to the head node
	 */
	public void first() {
		current = head;
	}

	/**
	 * Set current to the next node in the list
	 */
	public void next() {
		current = (current != null) ? current.getLink() : current;

	/**
	 * Return the element at the current node
	 * @return 			element stored at the current node
	 */
	}
		
	public Type getCurrent() {
		return (current != null) ? current.getData() : null;
	}

	/**
	 * Adds the specified element to the end of the list.
	 * Not possible for a full list.
	 * @param 			element - element to add to the linked list
	 */
	public void add(Type element) {
		// Don't add anything if the list is full
		if (!isFull()) {
			
		

		// If the list is empty, the new node becomes the head
		if (isEmpty()) {
			head = new Node<Type>();
			head.setData(element);
			current = head;
			tail = head;
		}

		// If the list is not empty, the new node is added to the end of the list
		else {
			tail.setLink(new Node<Type>());
			tail = tail.getLink();
			tail.setData(element);
		}

		// Increment the number of elements
		numElements++;

		}
	}

	// Overloaded add function that allows the user to insert
	// data into the linked list at a specific node
	public void add(int index, Type element) {
		if (!isFull() && index >= 0 && index <= numElements) {

			// create a new node
			Node<Type> newNode = new Node<Type>();
			newNode.setData(element);

			if (index == 0 && isEmpty()) {
				head = newNode;
				tail = newNode;
			}

			// If the index is 0 but the list is not empty
			// then we insert node at beginning of list
			else if (index == 0) {
				newNode.setLink(head);
				head = newNode;
			}

			// if the index is equal to the number of nodes
			// then we can add our new node to the tail of list
			else if (index == numElements) {
				tail.setLink(newNode);
				tail = newNode;
			}

			// if we have a list of several elements and our index
			// is between the first and last, then we can insert our node in the list
			// at that index

			else {

			// first we need to traverse our linked list to access the previous node
			current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.getLink();
			}

			Node<Type> previous = current;
			newNode.setLink(previous.getLink());
			previous.setLink(newNode);
		
			}

			numElements++;
	
		}

	}

	/**
	 * Returns the value in the node at the given index
	 * @param 			index - the position in the list to get the element from
	 * @return 			the element at index
	 */

	public Type get(int index) {
		// Don't traverse the list if index is out of bounds
		if (!(index < 0 || index >= numElements)) {
			Node<Type> temp = head;
			int i = 0;

			// Traverse the list starting at the head until index is reached
			while (i < index) {
				temp = temp.getLink();
				i++;
			}

			return temp.getData();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the value in the head node
	 * @return 			the value in the head node
	 */
	public Type getFirst() {
		return (head != null) ? head.getData() : null;
	}

	/**
	 * Returns the value in the tail node
	 * @return 			the value in the tail node
	 */
	public Type getLast() {
		return (tail != null) ? tail.getData() : null;
	}

	/**
	 * Removes the element at the specified index.
	 * Not possible for an empty list
	 * @param			index - the position in the list of the element to be removed
	 */
	public void remove(int index) {
		if (!isEmpty() && index >= 0 && index <= numElements - 1) {

			// removes the head node if the index is 0
			if (index == 0){
				head = head.getLink();
				if (head == null) {
					// if our head becomes null after removal then set tail to null too
					tail = null;
				}
			}


			else if (index == numElements - 1){
				// traverse the list until we reach node before node and set
				// its Link to null
				current = head;

				for (int i = 0; i < numElements - 2; i++) {
					current = current.getLink();
				}
				// making the node before tail the new tail and updating its link
				tail = current;
				tail.setLink(null);
			}


			else {

			// start at the head of list
			current = head;

			// traverse through list to node previous to the index
			for (int i = 0; i < index - 1; i++) {
				current = current.getLink();
			}


			// Now the previous node will skip the next node, deleting it from the list
			current.setLink(current.getLink().getLink());
			}

			numElements--;

		}
	}

	/**
	 * Replaces the element at the specified index with the given element
	 * @param			index - the position in the list of the element to replace
	 * @param			element - the element to replace the current element at index
	 */
	public void set(int index, Type element) {
		// check if index is in bounds
		if (index >= 0 && index <= numElements - 1) {

			// traverse the list to index and replace the data in the node
			current = head;

			for (int i = 0; i < index; i++) {
				current = current.getLink();
			}

			current.setData(element);
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element.
	 * Returns -1 if the list does not contain the element.
	 * @param			element - the element whose index is being searched for
	 * @return 			the index of element, or -1 if it doesn't exist
	 */
	public int indexOf(Type element) {
		// traverse the list and see if any node contains the sought after element
		current = head;

		for (int i = 0; i < numElements - 1; i++) {
			current = current.getLink();
			if (current.getData() == element) {
				return i + 1;
			}

		}

		return -1;
	}
}
