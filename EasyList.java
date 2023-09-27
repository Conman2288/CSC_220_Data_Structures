/*
Name: 		Connor Heard
Date: 		9/25/23
Course: 	CSC 220 - Data Structures
*/


public class EasyList extends AbstractEasyList {
	/**
	 * Default constructor - constructs an empty list of initial capacity MAX_SIZE.
	 * end is set to -1 to indicate the list is empty.
	 */
	public EasyList() {
		// instantiates an array of chars with a MAX_SIZE of 10
		list = new char[MAX_SIZE];
		end = -1;
	}

	/**
	 * Copy constructor - constructs an EasyList whose elements are copied from
	 * the given EasyList
	 * @param		el - EasyList to copy from
	 */
	public EasyList(EasyList el) {
		// TO-DO for Program 03
		// We want a hard copy for EasyList

		// Create a new array of the same size as el
		list = new char[el.size()];

		// set the end variable to reflect the size of the copied list
		end = el.size() - 1;

		// iterate through el and copy each element to new list
		for (int i = 0; i < el.size(); i++) {
			list[i] = el.get(i);
		}
	}

	/**
	 * Returns the number of elements in the list (not the max capacity)
	 * @return 		number of elements in list
	 */
	public int size() {
		// retunr n + 1 which is the size of our "list"
		return end + 1;
	}

	/**
	 * Returns a String representation of the list ("NULL" if empty)
	 * @return 		String representation of list
	 */
	public String toString() {
		// Return "NULL" if empty
		if (size() == 0) {
			return "NULL";
		}

		// Otherwise, list each element separated by a space
		else {
			String s = "";
			for (int i = 0; i < size(); i++) {
				s += list[i] + " ";
			}
			return s;
		}
	}

	/**
	 * Adds the specified element to the end of the list.
	 * Not possible for a full list.
	 * @param 		element - the element to be added to the list
	 */
	public void add(char element) {
		// only add element if there is room
		if (size() < MAX_SIZE) {
			end++;
			list[end] = element;
	}
}

	/**
	 * Adds the specified element at the specified position in the list.
	 * Elements are shifted to the right to make room.
	 * Not possible for a full list.
	 * @param 		index - the position in the list where the element will be added
	 * @param 		element - the element to be added to the list
	 */
	public void add(int index, char element) {
		// only add element if there is room and index is a valid location
		if (size() < MAX_SIZE && index >= 0 && index <= size()) {
			// Shift elements to the right until the desired index is reached
			for (int i = end; i >= index; i--) {
				list[i + 1]  = list[i];
			}

			// Add new element
			list[index] = element;
			end++;
		}
	}
	

	/**
	 * Returns the element at the specified index
	 * @param 		index - the position in the list of the element we are getting
	 * @return 		the element at index
	 */
	public char get(int index) {
		// if index is in range, return the element
		if (index >= 0 && index < size()) {
			return list[index];
		}

		// Otherwise, return some default value
		else {
			// In reality, you would want to throw an error here for a more robust implementation
			return ' ';
		}
	}

	/**
	 * Removes the element at the specified index.
	 * Elements are shifted to the left to fill in the space.
	 * Not possible for an empty list.
	 * @param 		index - the position in the list of the element we are removing
	 */
	public void remove(int index) {
		// This functions makes sure the index is in range and then removes
		// that element from the list
		if (size() > 0 && index >= 0 && index < size()) {
			for (int i = index; i < end; i++) {
				list[i] = list[i + 1];
			}
			end--;
		}
	}
 
	/**
	 * Replaces the element at the specified index with the element.
	 * Not possible for an empty list.
	 * @param 		index - the position in the list of the element we are replacing
	 * @param 		element - the element replacing the existing element at index
	 */
	public void set(int index, char element) {
		// checks if index is in range and then sets that value to the argument
		if (size() > 0 && index >= 0 && index < size()) {
			list[index] = element;
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element.
	 * Returns -1 if the list does not contain the element.
	 * @param 		element - the element being searched for
	 * @return 		the index of element, or -1 if it doesn't exist
	 */
	public int indexOf(char element) {
		// Loop through the list and return the index if element is found
		for (int i = 0; i < size(); i++) {
			if (list[i] == element) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Sorts the list in ascending order using insertion sort.
	 */
	public void sort() {
		// Utilizes the insertion sort algorithm to organize our list
		// Since we have a list of chars, we can use simple comparisons to
		// determine magnitude of characters
		int i = 1;

		while (i < size()) {
			if (list[i - 1] > list[i]) {
				char temp = list[i];
				int j = i - 1;
				while (j >= 0 && list[j] > temp) {
					list[j + 1] = list[j];
					j -= 1;
				}
				list[j + 1] = temp;
			}
			i += 1;
		}
	}

	// Checks if the abstract list is empty
	public boolean isEmpty() {
		if (end == -1) {
			return true;
		}

		else{
			return false;
		}
	}

	// Checks if the abstract list is full
	public boolean isFull() {
		if (size() >= MAX_SIZE) {
			return true;
		}

		else {
			return false;
		}
	}
}