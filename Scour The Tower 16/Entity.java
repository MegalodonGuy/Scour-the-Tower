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
    protected int block; 
    protected boolean dead; 
    protected int vulnerable=0; 
    protected int weakened=0; 
    private FightWorld world;
    
    public Entity(int maxHealth, int health,Deck deck, FightWorld world){
     this.maxHealth=maxHealth;
     this.health=health;
     this.deck=deck;
     dead=false;
     this.world =world; 
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
             if (card.getEnergy()>deck.getAvailableEnergy()){
                 return; 
             }
             if (card.getTarget()){
             hit(card.getDamage(),card.getVulnerable(),card.getWeaken()); 
             deck.playedCard(card);
            }
            else{
                world.cardUsedOnWorld(); 
            }
            }
        } 
        
    }
    
    public void hit(int damage,int vulnerable, int weaken){
        double dmgMod=1; 
        if (this.vulnerable>0){
        dmgMod*=1.5; 
        }
        int dmg=0;
        int tempBlock = (block-(damage*=dmgMod)); // take away health = to the damage with modifiers but remove the damage that can get blocked
        if (tempBlock<=0){ // if attack broke through block
            dmg=(-1*tempBlock);
            block=0; 
        }
        else{
            block=tempBlock;
        }
        health-=dmg; 
        this.vulnerable+=vulnerable; 
        this.weakened+=weaken;
        if (health<=0 && !dead){
            getWorld().removeObject(this); 
            dead=true; 
        }
    }
    public void heal (int health){ 
        this.health+=health;
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
    public void vulnerable(int vulnerable){
        this.vulnerable+=vulnerable; 
    }
    public void weaken(int weaken){
        this.weakened+=weaken; 
    }
    public void turnPassed(){
      this.block=0;
         if (vulnerable>0){
        vulnerable--; 
      }
      if (weakened>0){
        weakened--; 
      } 
    }
}
