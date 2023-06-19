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
    
    /**
     * makes allows cards to be veiwed, lays out how they should look depending on amount in hand
     */
    public static void updateCardVisuals(ArrayList<Object> hand, Deck deck, World world){
        hand = Util.cloneContents(deck.getHand());

        int cardSpacing=300;
        double temp=cardSpacing; 

        for (int x=0; x<hand.size(); x++){
            temp=cardSpacing*(Math.pow(1-0.2,x));
        }
        temp+=10;
        cardSpacing=(int)temp;
        int initialCardSpacing=200;
        int ySpacing=800;
        for (int i=0; i<hand.size(); i++){
            Card card =(Card)hand.get(i);
            if (card==Deck.getSelectedCard()){
                ySpacing-=200;
            }
            else{ 
                ySpacing=800;
            }
            world.addObject(card,initialCardSpacing+cardSpacing*i+1,ySpacing); 
            card.setLocation(initialCardSpacing+cardSpacing*i+1,ySpacing);
        }

        world.showText("Energy: "+deck.getAvailableEnergy()+"/"+deck.getMaxEnergy(),75,700);
    }
    
    /**
     * clones an array list, helpful for deck
     */
    public static ArrayList<Object> cloneContents(ArrayList<Object> cloner){
        ArrayList<Object> tempList= new ArrayList<Object>(); 
        for (int i=0; i<cloner.size(); i++){
            tempList.add(cloner.get(i)); 
        }  
        return tempList;
    }
    
}
