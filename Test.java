/*
Name:		Connor Heard
Date:		10/2/2023
Course:		CSC 220 - Data Structures
*/

import java.util.Random;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		testNode();
		testAdd();
		testGet();
		testCopyConstructor();
		testOverloadedAdd();
		testRemove();
		testSet();
		testIndexOf();
	}

	public static void testNode() {
		System.out.println("Testing the Node class.");

		// Create some nodes
		Node<String> n1 = new Node<String>();
		Node<String> n2 = new Node<String>();
		Node<String> n3 = new Node<String>();
		Node<String> n4 = new Node<String>();


		// Put some data in the nodes
		n1.setData("one");
		n2.setData("two");
		n3.setData("three");
		n4.setData("four");

		// Access data stored in one node
		System.out.println("Node 1 contains " + n1.getData());

		// Link two nodes together
		n1.setLink(n2);

		// Access the data stored in one node using another
		System.out.println("Node 2 contains " + n1.getLink().getData());

		// Link many nodes together
		n2.setLink(n3);
		n3.setLink(n4);

		// Iterate through a chain of nodes
		// Create a shallow copy of header node
		Node<String> currentNode = n1;
		int i = 1;
		while (currentNode != null) {
			System.out.println("Node " + i + " contains " + currentNode.getData());
			currentNode = currentNode.getLink();
			i++;
		}
		System.out.println();
	}


	public static void testCopyConstructor() {
		LinkedList<Character> test = new LinkedList<Character>();
		Random r = new Random();

		ArrayList<Character> letters = new ArrayList<Character>();
		for (char c = 'A'; c != '['; c++) letters.add(c);
		for (int i = 0; i < 10; i++)  test.add(letters.remove(r.nextInt(letters.size())));

		// Test the copy constructor
		LinkedList<Character> testCopy = new LinkedList<Character>(test);

		System.out.println("original list: " + test);
		System.out.println("copied list: " + testCopy);
	}


	public static void testAdd() {
		System.out.println("Testing the add() method: ");
		LinkedList<String> list = new LinkedList<String>();

		// Test printing an empty list
		System.out.println(list);

		// Test adding a single element to the list
		list.add("One");
		System.out.println(list);

		// Test that the method won't add more nodes when the list is full
		for (int i = 0; i < 20; i++) list.add("More");
		System.out.println(list);

	}

	public static void testOverloadedAdd() {
		LinkedList<Character> test = new LinkedList<Character>();
		Random r = new Random();

		// Create array list from A to Z
		ArrayList<Character> letters = new ArrayList<Character>();
		for (char c = 'A'; c != '['; c++) letters.add(c);

		// Test adding a letter to an empty list
		char c = letters.remove(r.nextInt(letters.size()));
		test.add(0, c);
		System.out.println("--- subtest 1: adding a letter to an empty list ---");
		System.out.println("Current list: " + test);

		// Test adding letter out of bounds
		c = letters.remove(r.nextInt(letters.size()));
		test.add(2, c);
		System.out.println("--- subtest 2: adding a letter at index 2 (out of bounds) ---");
		System.out.println("Current List: " + test);

		// Test adding letter to beginning of list
		c = letters.remove(r.nextInt(letters.size()));
		test.add(0, c);
		System.out.println("--- subtest 3: adding a letter to the beginning of the list (index 0) ---");
		System.out.println("Current list: " + test);

		// Test adding letter to middle of list
		c = letters.remove(r.nextInt(letters.size()));
		test.add(1, c);
		System.out.println("--- subtest 4: adding a letter to the middle of the list (index 1) ---");
		System.out.println("Current list: " + test);

		// Test adding letter to the end
		c = letters.remove(r.nextInt(letters.size()));
		test.add(3, c);
		System.out.println("--- subtest 5: adding to the end of the list (index 3) ---");
		System.out.println("Current List: " + test);

		// Test adding letter to a full list
		for (int i = 0; i < 6; i++) test.add(letters.remove(r.nextInt(letters.size())));
		c = letters.remove(r.nextInt(letters.size()));
		test.add(9, c);
		System.out.println("--- subtest 6: fill the list with letters, then try to add another (index 9) ---");
		System.out.println("Current list: " + test);
	}

	public static void testRemove() {
		LinkedList<Character> test = new LinkedList<Character>();
		Random r = new Random();

		// Create array list from A to Z
		ArrayList<Character> letters = new ArrayList<Character>();
		for (char c = 'A'; c != '['; c++) letters.add(c);

		for (int i = 0; i < 5; i++) test.add(letters.remove(r.nextInt(letters.size())));
		System.out.println("Initial List: " + test);

		// Test removing letter from the middle of the list
		test.remove(2);
		System.out.println("--- subtest 1: removing a letter from the middle of the list (index 2) ---");
		System.out.println("Current list: " + test);

		// Test removing letter from the end of the list
		test.remove(3);
		System.out.println("--- subtest 2: removing a letter from the end of the list (index 3) ---");
		System.out.println("Current list: " + test);

		// Test removing letter from the beginning of list
		test.remove(0);
		System.out.println("--- subtest 3: removing a letter from the beginning of the list (index 0) ---");
		System.out.println("Current list: " + test);

		// Test removing letter out of bounds
		test.remove(5);
		System.out.println("--- subtest 4: removing a letter at an out of bounds index (index 5) ---");
		System.out.println("Current list: " + test);
	}

	public static void testGet() {
		System.out.println("Testing the get() method: ");
		LinkedList<String> list = new LinkedList<String>();

		// Add some values to the list
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");

		// Test to see if get returns the correct value
		for (int i = 0; i < 4; i++) {
			System.out.println(list.get(i));
		}

		// Test to see if get returns NULL if the index is out of bounds
		System.out.println("This should say null: " + list.get(-1));
		System.out.println("This should say null: " + list.get(15));
	}

	public static void testSet() {
		LinkedList<Character> test = new LinkedList<Character>();
		Random r = new Random();

		// Create array list from A to Z
		ArrayList<Character> letters = new ArrayList<Character>();
		for (char c = 'A'; c != '['; c++) letters.add(c);

		for (int i = 0; i < 5; i++) test.add(letters.remove(r.nextInt(letters.size())));
		System.out.println("Initial List: " + test);

		// Test replacing a letter
		char c = letters.remove(r.nextInt(letters.size()));
		test.set(2, c);
		System.out.println("Current list: " + test);

	}

	public static void testIndexOf() {
		LinkedList<Character> test = new LinkedList<Character>();
		Random r = new Random();

		// Create array list from A to Z
		ArrayList<Character> letters = new ArrayList<Character>();
		for (char c = 'A'; c != '['; c++) letters.add(c);

		for (int i = 0; i < 5; i++) test.add(letters.remove(r.nextInt(letters.size())));
		System.out.println("Initial List: " + test);

		char c = test.get(r.nextInt(test.size()));
		System.out.println("--- subtest 1: element exists ---");
		System.out.println("Character " + c + " is at index " + test.indexOf(c));
	}

	
}