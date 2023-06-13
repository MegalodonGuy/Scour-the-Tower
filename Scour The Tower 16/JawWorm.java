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
    
    private double dmgMod;
    private double dmg;
    public JawWorm(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        firstMove=true;
        this.world=world;
        image = new GreenfootImage("JawWorm.png");
        image.scale(300,300);
        setImage(image);
        dmgMod=1;
        dmg=0;
    } 
    
    @Override
    protected void attackPattern(){
        if (weakened>0){
            dmgMod*=0.75;
        }
        if (dead){
            dmgMod=1;
            return; 
        }
        if (firstMove){
            move1(); 
            firstMove=false; 
            dmgMod=1;
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
        
        dmgMod=1;
    }
    protected void move1(){ //chomp
        dmg=11+strength;
        player.hit((int)(dmg*dmgMod),0,0);
        
        System.out.println("chomp");
    }
    protected void move2(){//thrash
      dmg=7+strength;
      player.hit((int)(dmg*dmgMod),0,0);
      block(5); 
      System.out.println("thrash");
    }
    protected void move3(){ // bellow
      increaseStrength(3);  
      block(5);
      System.out.println("bellow");
    }
    
}