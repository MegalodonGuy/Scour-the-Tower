import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    private GreenfootImage image;
    public Block(){
        image=this.getImage();
        image.scale(50,50);
        setImage(image);
    }
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
