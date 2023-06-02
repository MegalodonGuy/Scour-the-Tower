import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class Deck here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Deck extends Actor
{
    private ArrayList<Object> deck = new ArrayList<Object>(); //full deck
    private ArrayList<Object> hand = new ArrayList<Object>(); // current hand
    private ArrayList<Object> drawPile = new ArrayList<Object>(); // draw from here
    private ArrayList<Object> discardPile = new ArrayList<Object>(); // cards go here after used or turn ended
    private ArrayList<Object> exhaustPile = new ArrayList<Object>();// exhaust cards go here so they cannot reappear during the current fight after played
    private static Object selectedCard; // card selected to be used, one at a time
    
      public Deck(ArrayList<Object> deck){
        this.deck=Util.cloneContents(deck); 
        drawPile=Util.cloneContents(deck); 
        deal();
    }
    /**
     * Act - do whatever the Deck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    } 
    public void drawRandom(){
        if (drawPile.size()==0){
            drawPile=Util.cloneContents(discardPile); 
            discardPile.clear(); 
        }
        Collections.shuffle(drawPile);
        hand.add(drawPile.get(0)); 
        drawPile.remove(0); 
    }
    public void deal(){
        discardHand(); 
        for (int i=0; i<5; i++){
            drawRandom(); 
        }
    } 
    public void discardHand(){ 
        for (int i=0; i<hand.size(); i++){
         discardPile.add(hand.get(i));
         getWorld().removeObject((Card)hand.get(i)); // clears card off world so it can be reused
        }
        hand.clear(); 
    }
    public void discardCard(Object card){
        discardPile.add(card); 
        getWorld().removeObject((Card)card);
        hand.remove(card); 
    }
    
    public ArrayList<Object> getHand(){
        return hand;
    }
    public static Object getSelectedCard(){
        return selectedCard; 
    }
    
    public static void setSelected(Object card){
        selectedCard=card; 
    }
}
