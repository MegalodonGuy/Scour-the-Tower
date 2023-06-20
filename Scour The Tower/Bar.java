import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (Franklin G.) 
 * @version (a version number or a date)
 */
public class Bar extends Actor
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public int barMax;
    public int hP;
    public int barLength = 100;
    public int barHeight = 20;
    public int sizeForHP;
    public int hpPercentage=100;
    Entity entity;
    public Bar(Entity entity){
        this.entity = entity;
        barMax = 100;
        hP = entity.getHealth();
        sizeForHP = (int)barLength/barMax;
        setImage(new GreenfootImage(barLength + 2, barHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, barLength + 1, barHeight + 1);
        if(hP/barMax > 0.3){
            myImage.setColor(Color.GREEN);
        } else {
            myImage.setColor(Color.RED);
        }
        myImage.fillRect(1, 1, hP*sizeForHP, barHeight);
    }

    public void act()
    {
        hpPercentage=100*entity.getHealth()/entity.getMaxHealth();
        setBar(hpPercentage);
    }

    public void setBar(int currentHP){
        sizeForHP = (int)barLength/barMax;
        setImage(new GreenfootImage(barLength + 2, barHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.drawRect(0, 0, barLength + 1, barHeight + 1);
        if(currentHP/barMax > 0.3){
            myImage.setColor(Color.GREEN);
        } else {
            myImage.setColor(Color.RED);
        }
        myImage.fillRect(1, 1, currentHP*sizeForHP, barHeight);
    }

    public void loseHP(){
        hP--;
    }

    public void gainHP(){
        hP++;
    }
}
