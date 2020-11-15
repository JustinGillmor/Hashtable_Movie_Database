/*
 * @author Justin Gillmor
 * 
 * LList.java is the data structure that will provide the chaining wihtin the 
 * hashtable. After a collison within the hashtable, the LList can still store the movie
 * because the new LLNode can become the head of the LList and point in the direction
 * of the other LLNodes within the same hashkey on the hashtable.
 * 
 * Last Updated: 11/10/20
 */
public class LList 
{
	private LLNode head;
	private LLNode tail;
	
	//constructor
	public LList()
	{
		head = null;
	}
	
	/*
	 * Method add() will add a LLNode to the LList within the element of the hashtable
	 * This will change the head of the LList and point to the Node that was the head before.
	 */
	public void add(LLNode newNode)
	{
		if(head == null)
		{
			head = new LLNode(newNode.getContents(), null);
			tail = head;
		}
		if(head != null)
		{
			head = new LLNode(newNode.getContents(), head);
			
		}
	}
	
	/*
	 * Method get() will return a Movie after probing through the LList by creating a
	 * new LLNode until it reaches the index that is desired.
	 */
	public Movie get(int index)
	{
		LLNode current = new LLNode();
		current = head;
		int count = 0;
		
		while(current.getNext() != null)
		{
			if(count == index)
			{
				return current.getContents();
			}
			count++;
			current = current.getNext();
		}
		return null;
	}

	/*
	 * Method size() will return an integer that represents how many LLNodes(Movies)
	 * are within the element of the array. Using the similar probing method until 
	 * a null Node is found.
	 */
	public int size()
	{
		LLNode current = new LLNode();
		current = head;
		int count = 0;
		while(current != null)
		{
			count++;
			current = current.getNext();
		}
		return count;
	}
	
	/*
	 * Method remove() will "remove" the desired node depending on the index. After probing
	 * through the LList of Nodes, it will point the the Node prior to the Node_being_removed to the Node
	 * after the Node_being_removed ultimately skipping it.
	 */
	public void remove(int index)
	{
		LLNode current = new LLNode();
		current = head;
		int count = 0;
		if (index == 0)
		{
			head = head.getNext();
		}
		if (index > 0)
		{
			while (count < index - 1)
			{
				current = current.getNext();
				count++;
			}
			current.setNext(current.getNext().getNext());
		}
	}
}
