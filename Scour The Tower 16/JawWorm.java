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
    public JawWorm(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        firstMove=true;
        this.world=world;
    } 
    
    @Override
    protected void attackPattern(){
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
         player.hit(11,0,0);
    }
    protected void move2(){//thrash
      player.hit(7,0,0);
      block(5); 
    }
    protected void move3(){ // bellow
         
    }
}