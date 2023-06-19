import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sentry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sentry extends Enemy
{
    private FightWorld world;
    GreenfootImage image;
    private int patternNum;
    private int sentryNum;
    public Sentry(int health, int maxHealth,Deck deck, FightWorld world, Player player, int sentryNum){
        super(maxHealth, health, deck, world,player);
        this.world=world;
        this.sentryNum=sentryNum;
        image = new GreenfootImage("Sentry.png");
        image.scale(200,200);
        setImage(image);

        if (sentryNum==1){
            nextMove="Bolt";
            this.patternNum=0;
        }
        else {
            nextMove="Beam";
            this.patternNum=1;
        }
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }

        switch (patternNum){
            case 0:
                move1();
                patternNum++;
                break;
            case 1:
                move2();
                patternNum--;
                break;
        }

        nextMove();
    }

    protected void move1(){ 
        //System.out.println("Bolt");

        deck.addIntoDiscardPile(new Card(43));
        deck.addIntoDiscardPile(new Card(43));
    }

    protected void move2(){ 
        //System.out.println("Beam");
        player.hit(9+strength,0,0,weakened);
    }


    public void nextMove(){
        switch (patternNum){
            case 0:
                nextMove="Bolt";
                break;
            case 1:
                nextMove="Beam";
                break;
        }
    }
}
