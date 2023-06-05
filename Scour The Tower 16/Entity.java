import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    protected int maxHealth; 
    protected int health; 
    protected Deck deck; 
    private int block; 
    public Entity(int maxHealth, int health,Deck deck){
     this.maxHealth=maxHealth;
     this.health=health;
     this.deck=deck;
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)){
            // if card used on character
            if (Deck.getSelectedCard()!=null){
             Card card = (Card)Deck.getSelectedCard(); 
             damage(card.getDamage());
             
             deck.discardCard(card); 
            }
        }
    }
    
    public void damage(int damage){
        health-=damage;
        if (health<=0){
            getWorld().removeObject(this); 
        }
    }
    public void heal (int health){ 
        damage(-health);
    }
    public void reduceMaxHealth(int reduction){
        maxHealth-=reduction;
        if (health>maxHealth){ 
            health=maxHealth;
        }
    }
    public void increaseMaxHealth(int promotion){
        reduceMaxHealth(-promotion); 
    }
    public void block (int block){
        this.block+=block; 
    }
}
