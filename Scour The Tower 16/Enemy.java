import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that enemies will inherit from, contains health, max health 
 * and ways to be hit and killed
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    Player player;
    public Enemy(int maxHealth, int health,Deck deck, FightWorld world,Player player){
     super(maxHealth, health, deck, world);
     this.player=player; 
    }
    // default, overide in specific enemy 
    protected void attackPattern(){
        System.out.println("hey");
    }
    @Override
    public void turnPassed(){
      super.turnPassed();
       ((Enemy)this).attackPattern(); 
      
    }
}
