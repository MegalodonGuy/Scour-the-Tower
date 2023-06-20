import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinWorld here.
 * 
 * @author (Franklin G.) 
 * @version (a version number or a date)
 */
public class WinWorld extends World
{
    //Declare background image, victory image and continue button
    GreenfootImage image;
    GreenfootImage victory;
    private ContinueButton cButton;
    /**
     * Constructor for objects of class WinWorld.
     * 
     */
    public WinWorld(Player player)
    {    
        //Create a new world with 1000x800 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        
        //Set and scale background image
        image = new GreenfootImage("ActOne.png");
        image.scale(1000,800);
        getBackground().drawImage(image,0,0);
        
        //Set up victory image
        victory = new GreenfootImage("Victory.png");
        
        //Set up continue button
        cButton = new ContinueButton();
        
        //Add image, button and player
        getBackground().drawImage(victory, 500 - (victory.getWidth()/2), 150);
        addObject(cButton,500,650);
        addObject(player, 200, 400);
    }
}
