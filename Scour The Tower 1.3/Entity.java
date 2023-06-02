import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    protected int maxHealth; 
    protected int health;  
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here. 
    }
    
    public void damage(int damage){
        health-=damage;
        if (health<=0){
            //TODO die
        }
    }
    public void heal (int health){
        damage(-health);
    }
    public void reduceMaxHealth(int reduction){
        maxHealth-=reduction;
        if (health>maxHealth){
            health=maxHealth;
        }
    }
    public void increaseMaxHealth(int promotion){
        reduceMaxHealth(-promotion); 
    }
}
