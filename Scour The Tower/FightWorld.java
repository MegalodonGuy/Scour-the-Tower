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
    private ArrayList <Object> fullDeck = new ArrayList<Object>(); 
    private Deck deck;
    private Player player;
    private EndTurnButton etb;  
    private Energy energy;
    public static Label energyLabel;

    GreenfootImage image; 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public FightWorld(Player player, Deck deck, int levelNum,ArrayList fullDeck)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 

        image = new GreenfootImage("ActOne.png");

        this.levelNum=levelNum+1;

        image.scale(1000,800);
        getBackground().drawImage(image,0,0);
        Deck.setSelected(null);

        this.deck=deck;
        this.player=player;

        deck = new Deck(initialDeck);
        player = new Player(80,80,deck,this);
        energy = new Energy(3, 3, deck);
        energyLabel = new Label("3/3");

        int ran= ((int)(Math.random()*100))+1;
        if (this.levelNum<3){
            if (ran<=50){
                enemies.add(new JawWorm(42,42,deck,this,player));
            }
            else if (ran>50){
                enemies.add(new Cultist(42,42,deck,this,player));
            }
        }
        else if (this.levelNum==3){
            if (ran<=50){
                enemies.add(new Lagavulin(110,110,deck,this,player));
            }
            else if (ran>50){
                enemies.add(new Sentry(250,250,deck,this,player,1));
                enemies.add(new Sentry(250,250,deck,this,player,2));
                enemies.add(new Sentry(250,250,deck,this,player,1));
            }
        }
        else if (this.levelNum>3 && this.levelNum<6){
            if (ran<=50){
                enemies.add(new JawWorm(42,42,deck,this,player));
            }
            else if (ran>50){
                enemies.add(new Cultist(42,42,deck,this,player));
            }
        }
        else if (levelNum==6){
            enemies.add(new Hexaghost(250,250,deck,this,player));
        }
        else if (levelNum==7){
            System.out.println("You Win!");
        }

        etb = new EndTurnButton(deck); 
        addObject(this.deck,0,0);
        addObject(etb,900,700);

        addObject(player, 200,400);
        addObject(energy, 75,700);
        addObject(energyLabel, 74,700);

        addObject(this.player, 200,400);

        int decayFactor=enemies.size();

        // same system as the cards, they get bunched together if there is a lot of them
        int enemySpacing=(int)(300*(Math.pow(1-0.2,decayFactor)));
        for (int x =0; x<enemies.size(); x++){
            addObject((Entity)enemies.get(x), 800-enemySpacing*x,370);
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

        if (enemies.size()==0){
            if (levelNum<7){
                player.afterFight();
                player.setSpawned(false);
                deck.setEnergy(deck.getMaxEnergy());
                deck.reset();
                Greenfoot.setWorld(new FightWorld(player,deck,levelNum,fullDeck));
            }
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
        else if(card.getCardID()==47){
            player.metallicize(3);
        }

        int tempStrength=player.getStrength();
        if (card.getDamage()==0){ // so cards that dont attack aren't effected
            tempStrength=0;
        }
        if (!card.getTarget() &&!card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                int ran = (int)(Math.random()*enemies.size());
                ((Entity)enemies.get(ran)).hit(card.getDamage()+tempStrength,card.getVulnerable(),card.getWeaken(),player.getWeaken());
            }
            deck.playedCard(card);

        }
        else if (card.getAOE()){
            for (int i=0; i<card.getAttackNum(); i++){
                player.block(card.getBlock()); 
                for (int x=0; x< enemies.size(); x++){
                    int previousHealth=((Entity)enemies.get(x)).getHealth();
                    ((Entity)enemies.get(x)).hit(card.getDamage()+tempStrength,card.getVulnerable(),card.getWeaken(),player.getWeaken());
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
