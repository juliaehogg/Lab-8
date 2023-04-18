package utilities;


import utilities.LinkedList.Node;

public class LinkedList<T>
{
	public Node 	_head;
	public Node		_tail;
	protected int 	_size;

	public final class Node
	{
		public T 	_data;
		public Node _next;

		/**
		 * Creates a blank node
		 */
		public Node() 
		{
			this(null, null);
		}
		/**
		 * Creates a Node with data and a next attribute
		 * 
		 * @param data - data point
		 * @param next - location the node will point
		 */
		public Node(T data, Node next) 
		{
			_data = data;
			_next = next;
		}
	}
	
	/**
	 * Initializes a Linked List with a sentinel head and tail of size 0
	 */
	public LinkedList()
	{
		this.init();
	}

	/**
	 * Initializes a Linked List with a sentinel head and tail of size 0
	 */
	private void init()
	{
		_head = new Node(null, null);
		_tail = new Node(null, null);
		_head._next = _tail;

		_size = 0;
	}

	/**
	 * Checks to see if the Linked List contains any valid nodes
	 * 
	 * @return true if the Linked List is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}

	/**
	 * Clears the Linked List by routing head and tail back to one another. The floating nodes are
	 * collected by the trash collector
	 */
	public void clear()
	{
		init();
	}

	/**
	 * Number of valid nodes in the Linked List
	 * 
	 * @return number of valid nodes
	 */
	public int size()
	{
		return _size;
	}

	/**
	 * Adds a new node to the front of the list, does not allow null data
	 * 
	 * @param element - data of the new node
	 */
	public void addToFront(T element)
	{
		if(element == null) return;
		_head._next = new Node(element, _head._next);
		_size += 1;
	}

	/**
	 * Checks to see if the Linked List contains a target
	 * 
	 * @param target
	 * @return true if the Linked List contains the target, false otherwise
	 */
	public boolean contains(T target)
	{
		return previous(target) != null;
	}

	/**
	 * Returns the first Node previous a target value
	 * 
	 * @param target
	 * @return First Node previous the target
	 */
	private Node previous(T target)
	{
		return previous(target, _head);
	}

	/**
	 * Private method that returns the first Node previous to a target value
	 * 
	 * @param target
	 * @param n - beginning node
	 * @return Node previous the target value 
	 */
	private Node previous(T target, Node n)
	{
		if (n._next == _tail) return null; 
		if(n._next._data.equals(target)) return n;
		return previous(target, n._next);
	}

	/**
	 * Removes the first node with data matching the target
	 * 
	 * @param target
	 * @return true if the target was remove, false otherwise
	 */
	public boolean remove(T target)
	{
		Node n = previous(target);
		if(n != null)
		{
			//routes the nodes around the node holding target
			n._next = n._next._next;
			_size -= 1;
			return true;
		}
		return false;
	}

	/**
	 * Returns the last valid node in the Linked List
	 * 
	 * @return last valid node
	 */
	private Node last()
	{
		//loop till the next node is the tail
		Node n = _head;
		while (n._next != _tail) 
		{
			n = n._next;
		}
		return n;
	}
	
	/**
	 * Adds a new node to the back of the list, does not allow null data
	 * 
	 * @param element - data of the new element
	 */
	public void addToBack(T element)
	{
		if(element == null) return;
		
		//call last to loop to the end
		last()._next = new Node(element, _tail);
		_size += 1;
	}

	/**
	 * Builds the Linked List into a string
	 * 
	 * @return the linked list as a string
	 */
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		//loop to add all elements with a comma separating
		Node node = _head._next;
		while (node != _tail) 
		{
			sb.append(node._data.toString());
			sb.append(", ");
			node = node._next;
		}
		
		//remove extra white space comma
		if (_size > 0) 
		{
			sb.delete(sb.length()-2, sb.length());
		}
		
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Calls private method to reverses the Linked List
	 */
	public void reverse() 
	{
		reverse(_head._next, _tail);
	}
	
	/**
	 * Private method that reverses a linked list
	 * 
	 * @param current - first node in old section
	 * @param prev - first node in new section
	 */
	private void reverse(Node current, Node prev) 
	{
		if (current == _tail) 
		{
			//puts head at the beginning
			_head._next = prev;
			return;
		}
		//holds the next node
		Node nextNodeHolder = current._next;
		
		//points current to its previous
		current._next = prev;
		
		//reccursive call
		reverse(nextNodeHolder, current);
	}
}
