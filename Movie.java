/*
 * @author Justin Gillmor
 * 
 * Movie.java is an object that includes three strings that represent the title, year released,
 * and the running time. Each movie also has a hashkey which depends on the hashtable size and it 
 * describes which "bin" the movie will get placed into.
 * 
 * Last Updated: 11/10/20
 */
public class Movie 
{
    private String title;
    private String yearReleased;
    private String runningTime;
    private int    hashKey;
    
    public Movie() 
    {
        this ("", "", "");
    }
    
    //Complete constructor
    public Movie (String title, String yearReleased, String runningTime) 
    {
        this.title        = title.trim();
        this.yearReleased = yearReleased;
        this.runningTime  = runningTime;
    }
    
    //Copy constructor
    public Movie (Movie m) 
    {
        title        = m.title.trim();
        yearReleased = m.yearReleased;
        runningTime  = m.runningTime;
   }
   
    /*
     * method getFirstLetterCodes will get the integer values of the first intials of each word in the title.
     * these values will be later processed to generate the hashkey.
     */
    public int[] getFirstLetterCodes() 
    {
        String [] words;
        int [] result;
        words = title.split(" ");
        result = new int[words.length];
        for (int wordNbr = 0; wordNbr < words.length; wordNbr++) 
        {
            if (words.length != 0 && words[wordNbr].length() > 0) 
            {
                result[wordNbr] = Character.valueOf(words[wordNbr].charAt(0));
            }
        }
        return result;
    }
    
    public String getYearReleased() 
    {
        return yearReleased;
    }
    
    public String getRunningTime() 
    {
        return runningTime;
    }
    
    public String getTitle() 
    {
        return title;
    }
    
    public int getHashKey() 
    {
        return hashKey;
    }
    
    public String toString() 
    {
        return "\""+title + "\" Released in : " + yearReleased + " Running Time : " + runningTime + " minutes";
    }
    
    public void setHashKey(long newHashValue)
    {
    	hashKey = (int)newHashValue;
    }

}
