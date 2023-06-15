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
        int cardSpacing=150;
        double temp=cardSpacing; 
        for (int i=0; i<hand.size(); i++){
            temp/=1.1;
        }
        cardSpacing=(int)temp;
        int initialCardSpacing=200;
        for (int i=0; i<hand.size(); i++){
            Card card =(Card)hand.get(i);

            switch(i){
                case 0:
                    world.addObject(card,initialCardSpacing,700);     
                    break;
                case 1:
                    world.addObject(card,initialCardSpacing+cardSpacing,700); 
                    card.setLocation(initialCardSpacing+cardSpacing,700);
                    break;
                case 2:
                    world.addObject(card,initialCardSpacing+cardSpacing*2,700);  
                    card.setLocation(initialCardSpacing+cardSpacing*2,700);
                    break;
                case 3:
                    world.addObject(card,initialCardSpacing+cardSpacing*3,700); 
                    card.setLocation(initialCardSpacing+cardSpacing*3,700);
                    break;
                case 4:
                    world.addObject(card,initialCardSpacing+cardSpacing*4,700); 
                    card.setLocation(initialCardSpacing+cardSpacing*4,700);
                    break;
                case 5:
                    world.addObject(card,initialCardSpacing+cardSpacing*5,700);
                    card.setLocation(initialCardSpacing+cardSpacing*5,700);
                    break;
                case 6:
                    world.addObject(card,initialCardSpacing+cardSpacing*6,700);  
                    card.setLocation(initialCardSpacing+cardSpacing*6,700);
                    break;
                case 7:
                    world.addObject(card,initialCardSpacing+cardSpacing*7,700); 
                    card.setLocation(initialCardSpacing+cardSpacing*7,700);
                    break;
                case 8:
                    world.addObject(card,initialCardSpacing+cardSpacing*8,700);
                    card.setLocation(initialCardSpacing+cardSpacing*8,700);
                    break;
                case 9:
                    world.addObject(card,initialCardSpacing+cardSpacing*9,700); 
                    card.setLocation(initialCardSpacing+cardSpacing*9,700);
                    break;
            }

        }

        world.showText("Energy: "+deck.getAvailableEnergy()+"/"+deck.getMaxEnergy(),75,700);
    }

    public static ArrayList<Object> cloneContents(ArrayList<Object> cloner){
        ArrayList<Object> tempList= new ArrayList<Object>(); 
        for (int i=0; i<cloner.size(); i++){
            tempList.add(cloner.get(i)); 
        }  
        return tempList;
    }
}
