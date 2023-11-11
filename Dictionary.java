/*
Name:     	Connor Heard
Date:       11/6/2023
Course:     CSC 220 - Data Structures
*/

public class Dictionary extends AbstractDictionary {
	/**
	 * Default Constructor
	 */
	public Dictionary() {
		hashTable = new KVPair[MAX_SIZE];
		DELETED = new KVPair("DELETED", 0.0);
		numKVPairs = 0;
	}

	// Returns true if the Dictionary is empty or full, respectively
	public boolean isEmpty() {
		return numKVPairs == 0;
	}

	public boolean isFull() {
		return numKVPairs == MAX_SIZE;
	}
	

	/**
	 * Returns the number of key-value pairs in the dictionary
	 * @return 			the number of key-value pairs
	 */
	public int getSize() {
		return numKVPairs;
	}

	/**
	 * Returns a vertical string representation of the dictionary's hash table
	 * This includes null and deleted KVPairs
	 * @return 			String representation of the dictionary's hash table
	 */
	public String toString() {
		String s = "";
		for (KVPair kvp : hashTable) {
			if (kvp == null) {
				s += "-NULL-\n";
			}
			else if (kvp == DELETED) {
				s += "-DELETED-\n";
			}
			else {
				s+= kvp + "\n";
			}
		}

		return s;
	}

	// Runs the key through a simple numerical hashing algorithm, and the result is used to search through the hash table.
	// If a collision is detected then linear probing is used to find the next available space in the hash table.
	private int emptyHashIndex(String key) {
		// prehash the string by summing its ASCII values to get an integer
		int prehash = 0;
		for (char character : key.toCharArray()) {
			prehash += (int) character;
		}

		// Now we use the modulus operator to actually hash our prehash integer and linear probing to find an empty index
		int hashIndex = prehash % hashTable.length;
		while (hashTable[hashIndex] != null && hashTable[hashIndex] != DELETED) {
			hashIndex = (hashIndex + 1) % hashTable.length;
		}

		return hashIndex;
	}

		private int searchHashIndex(String key) {
		// prehash the string by summing its ASCII values to get an integer
		int prehash = 0;
		for (char character : key.toCharArray()) {
			prehash += (int) character;
		}

		// Now we use the modulus operator to actually hash our prehash integer and linear probing to find an empty index
		int hashIndex = prehash % hashTable.length;
		int originalIndex = hashIndex;

		// We probe through our hash map until we reach a value that equals the key
		// Else, if we wrap back around and find nothing we return -1
		while (hashTable[hashIndex] != null && !hashTable[hashIndex].getKey().equals(key)) {
			hashIndex = (hashIndex + 1) % hashTable.length;
			if (hashIndex == originalIndex) {
				return -1;
			}
		}

		return hashIndex;

	}

	/**
	 * Adds the key and value given to the dictionary as a KVPair by storing it in its proper position
	 * in the backend hash table.  Uses open addressing with linear probing to handle collisions.
	 * @param			key - the key of the KVPair to be added to the dictionary
	 * @param			value - the value of the KVPair to be added to the dictionary
	 */
	public void add(String key, double value) {
		// If the dictionary is not full, then we search through it for an empty hash index
		// and replace that value with a key/value pair object reference.
		if (!isFull()) {
			KVPair kv = new KVPair(key, value);
			int emptyIndex = emptyHashIndex(kv.getKey());
			hashTable[emptyIndex] = kv;
			numKVPairs++;
		}
	}

	/**
	 * Returns the value stored in the Dictionary for the given key
	 * @param			key - the key of the KVPair to lookup in the dictionary
	 * @return 			the value of the KVPair containing key
	 */
	public double get(String key) {
		// We search through the dictionary until we find the key and 
		// return the value associated with it.
		if (!isEmpty()) {
			int index = searchHashIndex(key);
			double value = hashTable[index].getValue();
			return value;
		}
		return -1;
	}

	/**
	 * Removes the KVPair associated with the given key
	 * @param			key - the key of the KVPair to remove from the dictionary
	 */
	public void remove(String key) {
		// We search through the dictionary for the key, and if it is found, we
		// replace it with a DELETED value as a placeholder to maintain the
		// integrity of the linear probing.
		if (!isEmpty()) {
			int index = searchHashIndex(key);
			hashTable[index] = DELETED;
			numKVPairs--;
		}
	}
}