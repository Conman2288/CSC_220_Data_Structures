/*
Name:     	Connor Heard
Date:       10/31/2023
Course:     CSC 220 - Data Structures
*/

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Sort extends AbstractSort {

	public static void main(String[] args) {
		Sort s = new Sort();

		int[] quickArray = s.generateRandomArray(10, 1);

		// Quick sort
		System.out.println("### Testing Quick Sort ###");
		System.out.println("Initial array: " + Arrays.toString(quickArray));
		s.quickSort(quickArray);
		System.out.println("Sorted array: " + Arrays.toString(quickArray));
	}

	/**
	 * Returns an array of random integers between 0-99 given a size and seed
	 * @param 			size - the desired size of the array being generated
	 * @param 			seed - the seed used to initialize the random number generator
	 * @return 			an array of random integers between 0-99 (without duplicates)
	 */
	public int[] generateRandomArray(int size, int seed) {
		Random r = new Random(seed);

		// create the choices bank
		ArrayList<Integer> bank = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) bank.add(i);

		// fill an array with random selections from the choices bank (no duplicates)
		int[] array = new int[size];
		for (int i = 0; i < size; i++) array[i] = bank.remove(r.nextInt(bank.size()));

		return array;
	}

	/**
	 * Public interface to the quick sort algorithm. 
	 * @param 			array - the array of integers to sort
	 */
	public void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	/**
	 * Recursive quickSort algorithm.  This version always chooses the first element as the pivot,
	 * but this is not the only or best method for choosing a pivot!
	 * Uses the private quickSort method to do the sorting.
	 * @param 			array - the array of integers to sort
	 * @param			first - the index of the first element in the current subarray being sorted
	 * @param			last - the index of the last element in the current subarray being sorted
	 */
	private void quickSort(int[] array, int first, int last) {
		// Choose a pivot number and position the left and right cursors
		int pivot = array[first];
		int leftCursor = first + 1;
		int rightCursor = last + 1;

		// Search for swaps until the two cursors meet
		while (leftCursor != rightCursor) {
			// Search for a value larger than the pivot with the left cursor
			while (leftCursor != rightCursor) {
				if (array[leftCursor] > pivot) break;
				leftCursor++;
				if (leftCursor > last) break;
			}

			// Search for a value smaller than the pivot with the right cursor
			while (leftCursor != rightCursor) {
				rightCursor--;
				if (array[rightCursor] < pivot) break;
			}

			// Swap values if the cursors haven't met yet
			if (leftCursor != rightCursor) {
				int temp = array[leftCursor];
				array[leftCursor] = array[rightCursor];
				array[rightCursor] = temp;
			}
		}

		// Swap the pivot with the value to the left of the cursors
		array[first] = array[leftCursor] - 1;
		array[leftCursor - 1] = pivot;

		// Recursively sort the subarray to the left of the cursors
		if ((leftCursor - 2) - first > 0) {
			quickSort(array, first, leftCursor - 2);
		}

		// Recursively sort the subarray to the right of the cursors
		if (last - rightCursor > 0) {
			quickSort(array, rightCursor, last);
		}
	}

	/**
	 * The shell sort algorithm.
	 * @param			array - the array of integers to sort
	 * @param			k - the spacing counter used by shell sort
	 */
	public void shellSort(int[] array, int k) {
		while (k > 0) {
			int i = 0;
			while (i < k) {
				insertionSort(array, k, i);
				i++;
			}
			k--;
		}
	}

	/**
	 * The heap sort algorithm.
	 * @param			array - the array of integers to sort
	 */
	public void heapSort(int[] array) {
		// create a new heap object
		Heap heap = new Heap();

		// add elements from the array into the heap
		for (int i = 0; i < array.length; i++) {
			heap.add(array[i]);
		}

		// overwrite the elements in the array with values from getMax()
		for (int j = array.length - 1; j >= 0; j--) {
			array[j] = heap.getMax();
		}
	}

	/**
	 * Sorts the list in ascending order using insertion sort.
	 */
	public void insertionSort(int[] array, int k, int i) {
		// Utilizes the insertion sort algorithm to organize our subarray in shell sort

		while (i < array.length - k) {
			// if an element in the sub array is larger than the next kth element
			// then we perform an insertion sort pass
			if (array[i] > array[i + k]) {
				int temp = array[i + k];
				int j = i;
				while (j >= 0 && array[j] > temp) {
					array[j + k] = array[j];
					j -= k;
				}
				array[j + k] = temp;
			}
			i += k;
		}
	}
}