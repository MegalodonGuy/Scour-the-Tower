import java.util.ArrayList;

/**
 * Write a description of class Util here.
 * 
 * @author (Ben) 
 * @version (a version number or a date)
 */
public class Util  
{
    /**
     * Constructor for objects of class Util
     */
    public Util()
    {
    }

    
    public static ArrayList<Object> cloneContents(ArrayList<Object> cloner){
        ArrayList<Object> tempList= new ArrayList<Object>(); 
        for (int i=0; i<cloner.size(); i++){
         tempList.add(cloner.get(i)); 
        } 
        return tempList;
    }
}
