/*
 * @author Justin Gillmor
 * 
 * HashTable.java is a data structure that is implemented using an array of LinkedLists.
 * Each element of the array indicates the hashkey "bin" for the Movies. If there is a
 * collision between two movies with the same hashkey, the linkedlist within the element
 * will add the movie to the Linkedlist.
 * 
 * Last Updated: 11/10/20
 */
public class HashTable
{
	private int size;
	private LList[] movieArray;
	private int numOfItems;
	
	//default constructor and instantiates every linkedlist within the Hashtable
	public HashTable()
	{
		movieArray = new LList[100];
		for(int i = 0; i < 100; i++)
		{
			movieArray[i] = new LList();
		}
	}
	
	//desired size constructor
	public HashTable(int inputedSize)
	{
		movieArray = new LList[inputedSize];
		for(int i = 0; i < inputedSize; i++)
		{
			movieArray[i] = new LList();
		}
	}
	
	/*
	 * Method insert will place a movie within the hashtable using a LLNode in
	 * order to handle possible collisions later on.
	 */
	public void insert(Movie movieToInsert)
	{
		int probe;
		probe = (movieToInsert.getHashKey());
		movieArray[probe].add(new LLNode(movieToInsert));
		numOfItems++;
	}
	
	/*
	 * Method contains will make a new Node to obtain a hashkey to begin the search process.
	 * Then the linked list within the array element searched one node at a time until
	 * it can return a boolean.  
	 */
	public boolean contains(Movie containMovie)
	{
		int probe;
		int itemPassed = 0;
		LLNode checkMovie = new LLNode(containMovie);
		probe = (checkMovie.getContents()).getHashKey();
		while(movieArray[probe] != null && (itemPassed < movieArray[probe].size()))
		{
			if (movieArray[probe].get(itemPassed).equals(containMovie))
			{
				return true;
			}
			probe = (probe + 1);
			itemPassed++;
		}
		return false;
	}
	
	/*
	 * Method remove will take a movie and probe through the Hashtable of the element
	 * specified and it will remove the given movie if it is found within the LList
	 */
	public Movie remove(Movie movieToRemove)
	{
		int probe;
		int itemPassed = 0;
		probe = movieToRemove.getHashKey();
		while(movieArray[probe] != null && (itemPassed < movieArray[probe].size()))
		{
			if(movieArray[probe].get(itemPassed).equals(movieToRemove))
			{
				Movie returnMovie = movieArray[probe].get(itemPassed);
				movieArray[probe].remove(itemPassed);
				numOfItems--;
				return returnMovie;
			}
			itemPassed++;
		}
		return null;
	}
	
	/*
	 * Method view is the method that provides the listing operation within the main method.
	 * It will iterate through each element of the array, and each node within each element, and 
	 * provide the toString and hashkey for each Movie found.
	 */
	public void view()
	{
		for(int i = 0; i < movieArray.length; i++)
		{
			for(int j = 0; j < movieArray[i].size() - 1; j++)
			{
				if(movieArray[i].get(j) != null)
				{
					Movie returnMovie = movieArray[i].get(j);
					System.out.println(returnMovie.toString() + " HashKey " + returnMovie.getHashKey());
				}
			}
		}
	}
	
	/*
	 * numOfItems is a value that is constantly kept track of during the processing
	 * of the hashtable. This method will return that value. 
	 */
	public int numOfElements()
	{
		return numOfItems;
	}
	
	/*
	 * Returns the LinkedList of the element that is provided
	 */
	public LList getLList(int index)
	{
		return movieArray[index];
	}
	
	/*
	 * Returns the size of the Linked list within the array element
	 */
	public int LListSize(int arrayIndex)
	{
		int count = 0;
		while (movieArray[arrayIndex].get(count) != null)
		{
			count++;
		}
		return count;
	}
	
	/*
	 * returns the array size
	 */
	public int size()
	{
		return movieArray.length;
	}
}
