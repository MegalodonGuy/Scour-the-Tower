import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ContinueButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ContinueButton extends Button
{
    private StartScreen startScreen;
    /**
     * Act - do whatever the ContinueButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        // makes new start screen
        startScreen =new StartScreen();
        if (Greenfoot.mouseClicked(this)){
                 buttonAction();  
            }
    }
    @Override 
    protected void buttonAction(){  
        //sets world as start screen made
        Greenfoot.setWorld(startScreen);
    }
    
    public StartScreen getStartScreen(){
        return startScreen;
    }
}
