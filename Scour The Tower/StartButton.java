import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
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
        fightWorld =new FightWorld(startScreen.getPlayer(),startScreen.getDeck(),0,startScreen.getFullDeck());
        if (Greenfoot.mouseClicked(this)){
             buttonAction();  
            }
    }
    @Override 
    protected void buttonAction(){  
        Greenfoot.setWorld(fightWorld);
    }
    
    public FightWorld getFightWorld(){
        return fightWorld;
    }
    
}
