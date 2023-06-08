import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class JawWorm here.
 * 
 * @author (Ben) 
 * @version (a version number or a date)
 */
public class JawWorm extends Enemy
{
    public JawWorm(int health, int maxHealth,Deck deck, FightWorld world){
        super(maxHealth, health, deck, world);
    } 
}