/*
Name:		Connor Heard
Date:		10/2/2023
Course:		CSC 220 - Data Structures
*/

public class Node<Type> {
	private Type data;
	private Node<Type> link;
	/**
	 * Default constructor - Newly constructed nodes have no data or link
	 */
	public Node() {
		data = null;
		link = null;
	}
 
	/**
	 * Accessor for the node's data
	 * @return 			data
	 */
	public Type getData() {
		return data;
	}

	/**
	 * Mutator for the node's data
	 * @param 			data - data to store in the node
	 */
	public void setData(Type data) {
		this.data = data;
	}

	/**
	 * Accessor for the node's link
	 * @return 			link
	 */
	public Node<Type> getLink() {
		return link;
	}

	/**
	 * Mutator for the node's link
	 * @param 			link - node to link to
	 */
	public void setLink(Node<Type> link) {
		this.link = link;
	}
}