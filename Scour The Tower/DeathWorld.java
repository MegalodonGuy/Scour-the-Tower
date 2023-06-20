import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class DeathWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathWorld extends World
{
    GreenfootImage image;
    GreenfootImage defeat;
    private ContinueButton cButton;
    
    /**
     * Constructor for objects of class DeathWorld.
     * 
     */
    public DeathWorld(Player player, ArrayList <Object> enemy)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        
        image = new GreenfootImage("DeathScene.jpg");
        image.scale(1000,800);
        getBackground().drawImage(image,0,0);
        
        defeat = new GreenfootImage("Defeat.png");
        
        cButton = new ContinueButton();
        
        getBackground().drawImage(defeat, 500 - (defeat.getWidth()/2), 150);
        addObject(cButton,500,650);
        addObject(player, 275, 500);
        
        int decayFactor=enemy.size();

        // same system as the cards, they get bunched together if there is a lot of them
        int enemySpacing=(int)(300*(Math.pow(1-0.2,decayFactor)));
        for (int x =0; x<enemy.size(); x++){
            addObject((Entity)enemy.get(x), 800-enemySpacing*x,370);
        }
    }
}
