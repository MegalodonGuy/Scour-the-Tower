import java.util.ArrayList;
import greenfoot.World;
import greenfoot.GreenfootImage;

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
    
    public static void updateCardVisuals(ArrayList<Object> hand, Deck deck, World world){
        hand = Util.cloneContents(deck.getHand());
        for (int i=0; i<hand.size(); i++){
            Card card =(Card)hand.get(i);
            world.addObject(card,250+120*i,750); 
            
            world.showText(""+deck.getMaxEnergy()+"/"+deck.getAvailableEnergy(),100,700);
        }
    }
    public static ArrayList<Object> cloneContents(ArrayList<Object> cloner){
        ArrayList<Object> tempList= new ArrayList<Object>(); 
        for (int i=0; i<cloner.size(); i++){
         tempList.add(cloner.get(i)); 
        }  
        return tempList;
    }
}
