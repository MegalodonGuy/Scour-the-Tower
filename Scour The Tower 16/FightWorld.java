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
    private ArrayList <Object> initialDeck = new ArrayList<Object>(); 
    private ArrayList <Object> hand = new ArrayList<Object>(); 
    private ArrayList <Object> enemies = new ArrayList<Object>();  
    private Deck deck;
    private Player player;
    private EndTurnButton etb;  
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
        enemies.add(new JawWorm(42,42,deck)); 
        player = new Player(80,80,deck);  
        etb = new EndTurnButton(deck); 
        
        addObject(deck,0,0);
        addObject(etb,900,700);
        addObject(player, 200,400);
        addObject((Entity)enemies.get(0), 800,300);
    }
 
    public void act(){
      Util.updateCardVisuals(hand, deck, this);
      if (etb.getTurnPassed()){
          ((Entity)enemies.get(0)).turnPassed();
          etb.setTurnPassed(false);
      }
      if (Greenfoot.mouseClicked(this) && Deck.getSelectedCard()!=null){
            // if card used on enemy
             Card card = (Card)Deck.getSelectedCard(); 
             if (!card.getTarget()){
                player.block(card.getBlock()); 
                ((Entity)enemies.get(0)).hit(card.getDamage(),card.getVulnerable(),card.getWeaken());
                deck.discardCard(card);
              }
        }
    }
}
