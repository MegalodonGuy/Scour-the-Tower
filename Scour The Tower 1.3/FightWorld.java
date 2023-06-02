import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class FightWorld extends World
{
    private ArrayList<Object> initialDeck = new ArrayList<Object>(); 
    private ArrayList<Object> hand = new ArrayList<Object>(); 
    private Deck deck;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public FightWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(3)); 
        
        
        deck = new Deck(initialDeck); 
        addObject(deck,0,0);
        addObject(new EndTurnButton(deck),900,700);
        addObject(new Player(80,80), 200,400);
        addObject(new JawWorm(42,42), 800,300);
    }
    
    public void act(){
      updateCardVisuals(); 
    }
    
    public void updateCardVisuals(){
        this.hand = Util.cloneContents(deck.getHand());
        for (int i=0; i<hand.size(); i++){ 
            Card card =(Card)hand.get(i);
            addObject(card,250+120*i,750); 
        }
    }
    
}
