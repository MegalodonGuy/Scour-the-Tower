import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class JawWorm here.
 * 
 * @author (Ben) 
 * @version (a version number or a date)
 */
public class JawWorm extends Enemy
{
    private boolean firstMove;
    private FightWorld world;
    GreenfootImage image;

    public JawWorm(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        firstMove=true;
        this.world=world;
        image = new GreenfootImage("JawWorm.png");
        image.scale(300,300);
        setImage(image);
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }
        if (firstMove){
            move1(); 
            firstMove=false; 
            return; 
        }

        int ran= ((int)(Math.random()*100))+1; 
        if (ran>=1 && ran<=25){
            move1(); 
        }
        else if(ran>= 26 && ran<=55){
            move2();
        }
        else {
            move3();
        }

    }

    protected void move1(){ //chomp
        player.hit(11+strength,0,0,weakened);
    }

    protected void move2(){//thrash
        player.hit(7+strength,0,0,weakened);
        block(5); 
    }

    protected void move3(){ // bellow
        increaseStrength(3);  
        block(5);
    }

}