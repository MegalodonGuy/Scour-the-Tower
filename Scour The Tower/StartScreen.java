import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{
    private ArrayList <Object> initialDeck = new ArrayList<Object>();
    private Deck deck;
    private Player player;
    private StartButton startButton;
    private int levelNum =0; 
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(980, 550, 1); 
        initialDeck.add(new Card(1)); 
        initialDeck.add(new Card(1));  
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(2)); 
        initialDeck.add(new Card(3));
        initialDeck.add(new Card(4));
        initialDeck.add(new Card(5));
        initialDeck.add(new Card(6));
        initialDeck.add(new Card(7));
        initialDeck.add(new Card(8));
        initialDeck.add(new Card(9));
        initialDeck.add(new Card(10));
        initialDeck.add(new Card(11));
        initialDeck.add(new Card(12));
        initialDeck.add(new Card(13));
        initialDeck.add(new Card(14));
        initialDeck.add(new Card(15));
        initialDeck.add(new Card(16));
        initialDeck.add(new Card(17));
        initialDeck.add(new Card(18));
        initialDeck.add(new Card(19));
        initialDeck.add(new Card(20));
        initialDeck.add(new Card(21));
        initialDeck.add(new Card(22));
        initialDeck.add(new Card(23));
        initialDeck.add(new Card(24));
        initialDeck.add(new Card(25));
        initialDeck.add(new Card(26));
        initialDeck.add(new Card(27));
        initialDeck.add(new Card(28));
        initialDeck.add(new Card(29));
        initialDeck.add(new Card(30));
        initialDeck.add(new Card(31));
        initialDeck.add(new Card(32));
        initialDeck.add(new Card(33));
        initialDeck.add(new Card(34));
        initialDeck.add(new Card(35));
        initialDeck.add(new Card(37));
        initialDeck.add(new Card(38));
        initialDeck.add(new Card(40));
        initialDeck.add(new Card(41));
        initialDeck.add(new Card(42));
        initialDeck.add(new Card(44));
        initialDeck.add(new Card(45));
        initialDeck.add(new Card(46));
        initialDeck.add(new Card(47));
        deck = new Deck(initialDeck);
        startButton=new StartButton(this);
        player = new Player(80,80,deck,startButton.getFightWorld());
        addObject(startButton,490,400);
    }
    
    public Player getPlayer(){
        return player;
    }
    public Deck getDeck(){
        return deck;
    }
    public ArrayList <Object> getFullDeck(){
        return initialDeck;
    }
}
