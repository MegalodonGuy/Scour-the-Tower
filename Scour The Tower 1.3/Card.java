import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cards here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    private int CardID; //which card is it? strike? defend? 
    
    private int damage;
    private int block;
    private int energy;
    
    private int vulnerable = 0;
    private int weaken = 0;
    
    private boolean upgraded = false; 
    public Card(int CardID){
        this.CardID = CardID;
        
        switch (CardID){
            case 1: 
            setImage("1.png"); 
            setStats(6,0,1); //attack damage, block amount and energy cost
            break;
            case 2:
            setImage("2.png");
            setStats(0,5,1);
            break;
            case 3:
            setImage("3.png");
            setStats(8,0,2);
            vulnerable=2; 
            break;
        }
    } 
    /**
     * Act - do whatever the Cards wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    private void setStats(int damage, int block,int energy){
        this.damage = damage; 
        this.block = block; 
        this.energy = energy; 
    }
    public void upgrade(){
        if (upgraded){
            return; 
        }
        switch (CardID){
            case 1: 
            damage+=3; 
            break;
            case 2:
            block+=3; 
            break;
            case 3:
            damage+=2;
            vulnerable+=1; 
            break;
        }
        upgraded=true; 
    }
    public int getCardID(){
        return CardID; 
    }
}
