/*
 * @author Justin Gillmor
 * 
 * LLNode.java stores a movie object and the next referenced LLNode.
 * LLNode provides a base for the methods within the LinkedList
 * 
 * 
 * Last Updated: 11/10/20
 */
public class LLNode 
{
	private Movie contents;
	private LLNode nextMovieNode;
	
	public LLNode() 
	{
		this(null, null);
	}

	public LLNode(Movie movieInNode) 
	{
		this(movieInNode, null);
	}

	public LLNode(Movie movieInNode, LLNode next) 
	{
		this.contents = movieInNode;
		this.nextMovieNode = next;
	}

	protected LLNode getNext() 
	{
		return nextMovieNode;
	}

	protected void setNext(LLNode next) 
	{
		this.nextMovieNode = next;
	}

	public Movie getContents() 
	{
		return contents;
	}

	public void setInfo(Movie movieInNode) 
	{
		this.contents = movieInNode;
	}

}
