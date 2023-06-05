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
    private Player player; 
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
        player = new Player(80,80,deck); 
        addObject(deck,0,0);
        addObject(new EndTurnButton(deck),900,700);
        addObject(player, 200,400);
        addObject(new JawWorm(42,42,deck), 800,300);
    }
 
    public void act(){
      updateCardVisuals();
      if (Greenfoot.mouseClicked(this)){
            // if card used on character
            if (Deck.getSelectedCard()!=null){
             Card card = (Card)Deck.getSelectedCard(); 
             if (!card.getTarget()){
                player.block(card.getBlock()); 
                deck.discardCard(card);
              }
            }
        }
    }
    
    public void updateCardVisuals(){
        this.hand = Util.cloneContents(deck.getHand());
        for (int i=0; i<hand.size(); i++){ 
            Card card =(Card)hand.get(i);
            addObject(card,250+120*i,750); 
        }
    }
    
}
