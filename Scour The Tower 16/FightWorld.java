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
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(3));
        initialDeck.add(new Card(4));
        initialDeck.add(new Card(5));
        initialDeck.add(new Card(6));
        initialDeck.add(new Card(7));
        initialDeck.add(new Card(8));
        initialDeck.add(new Card(9));
        initialDeck.add(new Card(10));
        initialDeck.add(new Card(11));
        initialDeck.add(new Card(12));
        initialDeck.add(new Card(13));
        initialDeck.add(new Card(14));
        initialDeck.add(new Card(15));
        initialDeck.add(new Card(16));
        initialDeck.add(new Card(17));
        initialDeck.add(new Card(18));
        initialDeck.add(new Card(19));
        initialDeck.add(new Card(20));
        initialDeck.add(new Card(21));
        initialDeck.add(new Card(22));
        initialDeck.add(new Card(23));
        initialDeck.add(new Card(24));
        initialDeck.add(new Card(25));
        initialDeck.add(new Card(26));
        initialDeck.add(new Card(27));
        initialDeck.add(new Card(28));
        initialDeck.add(new Card(29));
        initialDeck.add(new Card(30));
        initialDeck.add(new Card(31));
        initialDeck.add(new Card(32));
        initialDeck.add(new Card(33));
        initialDeck.add(new Card(34));
        initialDeck.add(new Card(35));
        initialDeck.add(new Card(37));
        initialDeck.add(new Card(38));
        initialDeck.add(new Card(40));
        initialDeck.add(new Card(41));
        initialDeck.add(new Card(42));
        initialDeck.add(new Card(44));
        initialDeck.add(new Card(45));
        initialDeck.add(new Card(46));

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
        Util.updateCardVisuals(hand, deck, this);
    }

    public void act(){
        Util.updateCardVisuals(hand, deck, this);
        for (int x=0; x< enemies.size(); x++){
            if(((Entity)enemies.get(x)).getDead()){
                enemies.remove(x);
            }
        }
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
        if (card==null){
            return;
        }
        if (card.getEnergy()>deck.getAvailableEnergy()){
            return; 
        }
        if (card.getCardID()==10){
            deck.drawRandom();
        }
        else if(card.getCardID()==18){
            player.increaseStrength(2);
        }
        else if(card.getCardID()==25){
            player.takeStaticDamage(6);
            deck.drawRandom();
            deck.drawRandom();
            deck.drawRandom();
            deck.gainEnergy(2);
        }
        else if(card.getCardID()==26){
            deck.gainEnergy(2);
        }
        else if(card.getCardID()==27){
            player.takeStaticDamage(3);
            deck.gainEnergy(2);
        }
        else if(card.getCardID()==27){
            player.takeStaticDamage(3);
            deck.gainEnergy(2);
        }
        else if(card.getCardID()==29){
            player.increaseStrength(player.getStrength());
        }
        else if(card.getCardID()==30){
            player.block(player.getBlock());
        }
        else if(card.getCardID()==35){
            deck.addIntoDiscardPile(new Card(36));
        }
        else if(card.getCardID()==38){
            deck.addIntoHand(new Card(39));
            deck.addIntoHand(new Card(39));
        }
        else if(card.getCardID()==41){
            int ran = (int)(Math.random()*deck.getHand().size());
            deck.exhaustCard((Card)deck.getHand().get(ran));
        }
        else if(card.getCardID()==44){
            deck.drawRandom();
            deck.drawRandom();
            deck.drawRandom();
            deck.battleTrance();
        }
        else if(card.getCardID()==45){
            player.barricade();
        }
        else if(card.getCardID()==46){
            player.demonForm();
        }
        

        if (!card.getTarget() &&!card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                int ran = (int)(Math.random()*enemies.size());
                ((Entity)enemies.get(ran)).hit(card.getDamage()+player.getStrength(),card.getVulnerable(),card.getWeaken(),player.getWeaken());
            }
            deck.playedCard(card);

        }
        else if (card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                for (int x=0; x< enemies.size(); x++){
                    int previousHealth=((Entity)enemies.get(x)).getHealth();
                    ((Entity)enemies.get(x)).hit(card.getDamage()+player.getStrength(),card.getVulnerable(),card.getWeaken(),player.getWeaken());
                    int newHealth=((Entity)enemies.get(x)).getHealth();
                    if(card.getCardID()==19){
                        player.heal(previousHealth-newHealth); // sorta bad way to do it but it works
                    }
                }
            }
            deck.playedCard(card);
        }

    }
    public Player getPlayer(){
        return player; 
    }
}
