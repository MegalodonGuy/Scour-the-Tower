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
    public Label(String text)
    {
        //Constructor for labels
        //Creates image big enough for text and then sets the image as text
        GreenfootImage img = new GreenfootImage (text.length()*20, 30);
        setImage (new GreenfootImage(text, 30, Color.WHITE, new Color(0,0,0,0) ));
    }
    public void setText(String newText){
        //sets the image with the new desired text
        setImage(new GreenfootImage(newText, 30, Color.WHITE, new Color(0,0,0,0) ));
    }
    public void act()
    {
        //No action code for labels 
    }
    public void removeLabel(){
        //This is for removing the label
        //Used for when the actor it is associated with is removed
        //Such as enemy health bars
        getWorld().removeObject(this);
    }
}
