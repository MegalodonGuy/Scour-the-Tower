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
        image.scale(200,200);
        setImage(image);
        nextMove="Chomp";
        ran=0;
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }
        // has first move as chomp then randomizes attack after
        if (firstMove || ran==0){
            move1(); 
            firstMove=false; 
            ran= ((int)(Math.random()*100))+1;
            nextMove();
            return; 
        }
         
        if (ran>=1 && ran<=25){
            move1(); 
        }
        else if(ran>= 26 && ran<=55){
            move2();
        }
        else {
            move3();
        }
        
        ran= ((int)(Math.random()*100))+1;
        nextMove();
    }

    protected void move1(){ //chomp
        player.hit(11+strength,0,0,weakened);
        //System.out.println("Chomp");
    }

    protected void move2(){//thrash
        player.hit(7+strength,0,0,weakened);
        block(5); 
        //System.out.println("Thrash");
    }

    protected void move3(){ // bellow
        increaseStrength(3);  
        block(6);
        //System.out.println("Bellow");
    }
    
    public void nextMove(){
        if (ran>=0 && ran<=25){
            nextMove="Chomp"; 
        }
        else if(ran>= 26 && ran<=55){
            nextMove="Thrash";
        }
        else if (ran>=56) {
            nextMove="Bellow";
        }
    }

}