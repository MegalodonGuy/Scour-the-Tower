import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bar extends Actor
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int barMax;
    public int barLength = 100;
    public int barHeight = 20;
    public int sizeForHP;
    public void act()
    {
        setBar(10, 10);
    }
    public void setBar(int HP, int max){
        barMax = max;
        sizeForHP = (int)barLength/barMax;
        setImage(new GreenfootImage(barLength + 2, barHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, barLength + 1, barHeight + 1);
        if(HP/max >= 0.3){
            myImage.setColor(Color.GREEN);
        } else {
        myImage.setColor(Color.RED);
        }
        myImage.fillRect(1, 1, barLength, barHeight);
    }
}
