import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathWorld extends World
{
    GreenfootImage image;
    private ContinueButton cButton;
    
    /**
     * Constructor for objects of class DeathWorld.
     * 
     */
    public DeathWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        
        image = new GreenfootImage("DeathScene.jpg");
        image.scale(1000,800);
        getBackground().drawImage(image,0,0);
        
        cButton = new ContinueButton();
        
        addObject(cButton,500,650);
    }
}
