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
    private boolean selected = false;
    private boolean target; // is it a non target or target card?
    private boolean aoe; // does it effect every enemy?
    public Card(int CardID){
        this.CardID = CardID;
        switch (CardID){
            case 1: 
            setImage("1.png"); 
            setStats(6,0,1,true); //attack damage, block amount and energy cost target or non target
            break;
            case 2:
            setImage("2.png");
            setStats(0,5,1,false);
            break;
            case 3:
            setImage("3.png");
            setStats(8,0,2,true);
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
        if (Greenfoot.mouseClicked(this)){
             if (!selected){
             Deck.setSelected(this);
             selected=true;
            }
            else{
             Deck.setSelected(null);  
             selected=false;
            }
            }
    }
    private void setStats(int damage, int block,int energy, boolean target){
        this.damage = damage; 
        this.block = block; 
        this.energy = energy; 
        this.target = target; 
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
    public int getDamage(){
        return damage; 
    }
    public int getEnergy(){
        return energy; 
    }
    public int getBlock(){
        return block; 
    }
    public int getVulnerable(){
        return vulnerable; 
    }
    public int getWeaken(){
        return weaken; 
    }
    public void deselect(){
        selected=false;
    }
    public boolean getTarget(){
        return target; 
    }
}
