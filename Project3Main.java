/*
 * @author Justin Gillmor
 * 
 * Project3Main prompts the user for a hashtable size, then it will place all of the movies 
 * within the file into the hashtable using the hashing function. Lastly, the program will 
 * continuously prompt the user for a command in which they can either Add a movie, List all movies, Get a movie,
 * , show Occupancy, Find a movie, and Quit. After quiting the program it will output the amount of times the get
 * and find commands were used along with the amount of movies searched.
 * 
 * Last Updated: 11/15/20
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project3Main 
{
	public static void main(String Args[])
	{
		/*
		 * Creates a hashtable of the user's choice, then automatically places each movie
		 * into it's own object and places it within the hashtable database
		 */
		Project3Main mainInstance = new Project3Main();
		Scanner input = new Scanner(System.in);
		System.out.print("What is the size of the hash table>> ");
		int hashTableSize = input.nextInt();
		HashTable database = new HashTable(hashTableSize);
		File movieFile = new File("movies");
		try 
		{
			Scanner scan = new Scanner(movieFile);
			try 
			{
				while(scan.hasNextLine())	
				{
					String title = scan.nextLine();
					String year = scan.nextLine();
					String runningTime = scan.nextLine();
					Movie newMovie = new Movie(title, year, runningTime);
					newMovie.setHashKey(mainInstance.findHashKey(newMovie, hashTableSize));
					database.insert(newMovie);
				}
			}catch(NoSuchElementException e) {}
		}catch(FileNotFoundException e) {System.out.print("File not found\n");}
	
		
		/*
		 * This next portion of the Main method loops through the commands given by the user
		 * Depending on the command given, the program will alter the hashtable database, and
		 * save the changes after each iteration.	
		 */
		boolean quit = false;
		int getCommandCount = 0;
		int findCommandCount = 0;
		int moviesSearched = 0;
	
		while(quit == false)
		{
			System.out.print("Command>> ");
			char command = input.next().charAt(0);
			
			//Add operation prompts the user for the movie descriptions and adds to the database
			if((command == 97) || (command == 65))
			{
				Movie newMovie = mainInstance.addMoviePrompt();
				newMovie.setHashKey(mainInstance.findHashKey(newMovie, hashTableSize));
				database.insert(newMovie);
				System.out.print("Movie successfully added\nTotal Movies: " + database.numOfElements() + "\n");
			}
			
			//List operation will list all of the movies within the database
			if((command == 76) || (command == 108))
			{
				database.view();
			}
			
			//Get operation finds and removes a movie based on the user inputed title
			if((command == 71) || (command == 103))
			{
				getCommandCount++;
				boolean removedOrFound = false;
				
				System.out.print("Enter Title>> ");
				input.nextLine();
				String checkTitle = input.nextLine();
				Movie check = new Movie(checkTitle, "", "");
				check.setHashKey(mainInstance.findHashKey(check, hashTableSize));
				int hashCheck = check.getHashKey();
				for(int i = 0; i < database.getLList(hashCheck).size(); i++)
				{
					moviesSearched++;
					try 
					{
						String databaseTitle = database.getLList(hashCheck).get(i).getTitle();
						if (databaseTitle.compareTo(checkTitle) == 0)
						{
							database.remove(database.getLList(hashCheck).get(i));
							System.out.print("Successfully Removed\nTotal Movies: " + database.numOfElements() +"\n");
							removedOrFound = true;
							break;
						}
					}catch(NullPointerException e) {} 
				}
				if(removedOrFound == false)
				{
					System.out.print("Sorry the movie was not found\n");
					moviesSearched--;
				}
			}
			
			//Occupancy operation shows the decimal in which how full the database is
			if((command == 79) || (command == 111))
			{
				System.out.println((double)database.numOfElements()/(double)database.size());
			}
			
			//Find operation, finds movie based on user inputted title
			if((command == 70) || (command == 102))
			{
				findCommandCount++;
				boolean removedOrFound = false;
				System.out.print("Enter Title>> ");
				input.nextLine();
				String checkTitle = input.nextLine();
				Movie check = new Movie(checkTitle, "", "");
				check.setHashKey(mainInstance.findHashKey(check, hashTableSize));
				int hashCheck = check.getHashKey();
				for(int i = 0; i < database.getLList(hashCheck).size(); i++)
				{
					moviesSearched++;
					try 
					{
						String databaseTitle = database.getLList(hashCheck).get(i).getTitle();
						if (databaseTitle.compareTo(checkTitle) == 0)
						{
							System.out.print("Successfully Found\nHash Key: " + database.getLList(hashCheck).get(i).getHashKey() +"\n");
							removedOrFound = true;
							break;
						}
					}catch(NullPointerException e) {} 
				}
				if(removedOrFound == false)
				{
					System.out.print("Sorry the movie was not found\n");
					moviesSearched--;
				}
			}
			
			//Quit (output counters)
			if((command == 81) || (command == 113))
			{
				System.out.println("Total Get Commands: " + getCommandCount);
				System.out.println("Total Find Commands: " + findCommandCount);
				System.out.println("Total movies visited: " + moviesSearched);
				quit = true;
			}	
		}
		input.close();
	}
	
	/*
	 * Method findHashKey will take a movie and the table size and it will generate
	 * a hashkey after computing the function within the for loop.
	 */
	public long findHashKey(Movie movieToHash, int hashTableSize)
	{
		int [] letterCode;
		letterCode = movieToHash.getFirstLetterCodes();
		long hashValue = letterCode[0];
		 for (int i = 1; i < letterCode.length; i++)
		 {
			 hashValue *= 128;
			 hashValue += letterCode[i];
		 }
		 return hashValue % hashTableSize;
	}
	
	/*
	 * Method addMoviePrompt returns a Movie after prompting the user for the details
	 */
	public Movie addMoviePrompt() 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Title>> ");
		String title = input.nextLine();
		System.out.print("Enter Year>> ");
		String year = input.next();
		System.out.print("Enter Running Time>> ");
		String runningTime = input.next();
		Movie newMovie = new Movie(title, year, runningTime);
		input.close();
		return newMovie;
	}
	
}
