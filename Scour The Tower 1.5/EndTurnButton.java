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
    public EndTurnButton(Deck deck){
     this.deck=deck;           
    }
    @Override 
    protected void buttonAction(){
        deck.deal(); 
    }
}
