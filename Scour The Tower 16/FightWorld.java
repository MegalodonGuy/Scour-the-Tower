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

    GreenfootImage image; 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public FightWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 

        image = new GreenfootImage("ActOne.png");

        image.scale(1000,800);
        getBackground().drawImage(image,0,0);

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
        initialDeck.add(new Card(4));
        initialDeck.add(new Card(5));

        deck = new Deck(initialDeck);
        player = new Player(80,80,deck,this);

        enemies.add(new JawWorm(42,42,deck,this,player));
        enemies.add(new JawWorm(42,42,deck,this,player));

        etb = new EndTurnButton(deck); 

        addObject(deck,0,0);
        addObject(etb,900,700);
        addObject(player, 200,400);
        for (int x =0; x<enemies.size(); x++){
            addObject((Entity)enemies.get(x), 800-250*x,400);
        }
    }

    public void act(){
        Util.updateCardVisuals(hand, deck, this);
        if (etb.getTurnPassed()){
            for (int x=0; x< enemies.size(); x++){
                ((Entity)enemies.get(x)).turnPassed();
            }
            ((Entity)player).turnPassed();
            etb.setTurnPassed(false);
        }
        if (Greenfoot.mouseClicked(this) && Deck.getSelectedCard()!=null){
            // if card used on enemy
            cardUsedOnWorld(); 
        }
    }

    public void cardUsedOnWorld(){
        Card card = (Card)Deck.getSelectedCard();
        if (card.getEnergy()>deck.getAvailableEnergy()){
            return; 
        }

        if (!card.getTarget() &&!card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                int ran = (int)Math.random()*enemies.size(); 
                ((Entity)enemies.get(ran)).hit(card.getDamage()+player.getStrength(),card.getVulnerable(),card.getWeaken());
            }
            deck.playedCard(card);
        }
        else if (card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                for (int x=0; x< enemies.size(); x++){
                    ((Entity)enemies.get(x)).hit(card.getDamage()+player.getStrength(),card.getVulnerable(),card.getWeaken());
                }
            }
            deck.playedCard(card);
        }

    }
    public Player getPlayer(){
        return player; 
    }
}
