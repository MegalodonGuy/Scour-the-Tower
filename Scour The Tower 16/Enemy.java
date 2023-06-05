import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that enemies will inherit from, contains health, max health 
 * and ways to be hit and killed
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    public Enemy(int maxHealth, int health,Deck deck){
     super(maxHealth, health, deck);
    }
}
