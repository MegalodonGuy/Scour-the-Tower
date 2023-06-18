import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class EndTurnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndTurnButton extends Button
{
    private Deck deck;
    private boolean turnPassed; 
    public EndTurnButton(Deck deck){
     this.deck=deck;  
     turnPassed=false; 
    }
    @Override 
    protected void buttonAction(){  
        deck.deal(); 
        turnPassed=true; 
    }
    
    public boolean getTurnPassed(){
        return turnPassed; 
    }
    public void setTurnPassed(boolean turnPassed){
        this.turnPassed=turnPassed; 
    }
}
