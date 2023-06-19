import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cultist here.
 * 
 * @author (Ben H) 
 * @version (a version number or a date)
 */
public class Cultist extends Enemy
{
    private boolean firstMove;
    private FightWorld world;
    GreenfootImage image;
    public Cultist(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        firstMove=true;
        this.world=world;
        image = new GreenfootImage("CawCaw.png");
        image.scale(200,200);
        setImage(image);
        nextMove="Incantation";
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }
        if (firstMove){
            move1(); 
            firstMove=false; 
        }
        else{
            move2();
        }
        nextMove();
    }

    protected void move1(){ 
        incantation(3);
        //System.out.println("Incantation");
    }

    protected void move2(){
        player.hit(6+strength,0,0,weakened);
        //System.out.println("Dark Strike");
    }
    
    // finds next move
    public void nextMove(){
        if (firstMove){
            nextMove="Incantaition";
        }
        else{
            nextMove="Dark Strike";
        } 
    }
}
