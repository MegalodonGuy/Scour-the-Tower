import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class StartButton extends Button
{
    private FightWorld fightWorld;
    private StartScreen startScreen;
    StartButton(StartScreen startScreen){
        this.startScreen=startScreen;
    }
    
    public void act(){
        // makes new fight world based on player and deck made in start screen, next level will be level 1
        fightWorld =new FightWorld(startScreen.getPlayer(),startScreen.getDeck(),2,startScreen.getFullDeck());
        if (Greenfoot.mouseClicked(this)){
             buttonAction();  
            }
    }
    @Override 
    protected void buttonAction(){  
        //sets world as fight world made
        Greenfoot.setWorld(fightWorld);
    }
    
    public FightWorld getFightWorld(){
        return fightWorld;
    }
    
}
