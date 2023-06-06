import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    public Player(int maxHealth, int health,Deck deck){
     super(maxHealth, health, deck);
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    @Override
    public void hit(int damage,int vulnerable, int weaken){
        super.hit(damage, vulnerable, weaken); 
        if (health<=0){
            //lose world 
        }
    }
    
}
