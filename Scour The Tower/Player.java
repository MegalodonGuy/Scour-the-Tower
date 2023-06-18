import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    FightWorld world;
    GreenfootImage image;
    public Player(int maxHealth, int health,Deck deck, FightWorld world){
        super(maxHealth, health, deck, world);
        this.world=world; 
        image = new GreenfootImage("Ironclad.png");
        image.scale(300,200);
        setImage(image);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (!spawned){
            world.addObject(new Bar(this), getX()+40, getY()+105);
            spawned=true;
        }
        if (Greenfoot.mouseClicked(this)){
            world.cardUsedOnWorld(); 
        } 
    }

    @Override
    public void hit(int damage,int vulnerable, int weaken, int attackerWeakend){
        super.hit(damage, vulnerable, weaken, attackerWeakend); 
        if (health<=0){
            //lose world 
        }
    }
    
    public int getStrength(){
        return strength;
    }
    public int getDex(){
        return dex;
    }
    
}
