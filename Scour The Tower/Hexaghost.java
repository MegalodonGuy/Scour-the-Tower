import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hexaghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hexaghost extends Enemy
{
    private FightWorld world;
    GreenfootImage image;
    private int patternNum=-1;
    public Hexaghost(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        this.world=world;
        image = new GreenfootImage("Hexaghost.png");
        image.scale(200,200);
        setImage(image);
        nextMove="Activate";
        ran=0;
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }
        //goes thru 2 starting moves and repeat 1-->7
        switch (patternNum){
            case -1:
                move1();
                patternNum++;
                break;
            case 0:
                move6();
                patternNum++;
                break;
            case 1:
                move2();
                patternNum++;
                break;
            case 2:
                move3();
                patternNum++;
                break;
            case 3:
                move2();
                patternNum++;
                break;
            case 4:
                move4();
                patternNum++;
                break;
            case 5:
                move3();
                patternNum++;
                break;
            case 6:
                move2();
                patternNum++;
                break;
            case 7:
                move5();
                patternNum=1;
                break;
        }

        nextMove();
    }

    protected void move1(){ 
        //System.out.println("Activate");
    }

    protected void move2(){
        player.hit(6+strength,0,0,weakened);
        //add burn
        deck.addIntoDiscardPile(new Card(36));
        //System.out.println("Sear");
    }

    protected void move3(){
        player.hit(5+strength,0,0,weakened);
        player.hit(5+strength,0,0,weakened);
        //System.out.println("Tackle");
    }

    protected void move4(){
        increaseStrength(2);  
        block(12);
        //System.out.println("Inflame");
    }

    protected void move5(){
        player.hit(6+strength,0,0,weakened);
        player.hit(6+strength,0,0,weakened);
        //add burns
        deck.addIntoDiscardPile(new Card(36));
        deck.addIntoDiscardPile(new Card(36));
        deck.addIntoDiscardPile(new Card(36));
        deck.upgradeAllBurns();
        //System.out.println("Inferno");
    }

    protected void move6(){
        player.hit(((player.getHealth()/12)+1)*6,0,0,weakened);
        //System.out.println("Divider");
    }

    public void nextMove(){

        switch (patternNum){
            case -1:
                nextMove="Activate";
                break;
            case 0:
                nextMove="Divider";
                break;
            case 1:
                nextMove="Sear";
                break;
            case 2:
                nextMove="Tackle";
                break;
            case 3:
                nextMove="Sear";
                break;
            case 4:
                nextMove="Inflame";
                break;
            case 5:
                nextMove="Tackle";
                break;
            case 6:
                nextMove="Sear";
                break;
            case 7:
                nextMove="Inferno";
                break;
        }
    }
}

