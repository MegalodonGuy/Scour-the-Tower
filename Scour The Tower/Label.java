import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    /**
     * Act - do whatever the Label wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Label(String text){
        setImage(new GreenfootImage(text, 35, Color.WHITE, new Color(0,0,0,0) ));  
    }
    public void act()
    {
        // Add your action code here.
    }
    public void setText(String newText){
        setImage(new GreenfootImage(newText, 35, Color.WHITE, new Color(0,0,0,0) ));  
    }
}
