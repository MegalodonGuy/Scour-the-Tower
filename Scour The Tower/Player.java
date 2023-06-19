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
        spawned=false;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // dont use cards on player, use on world if clicked
        if (!spawned){
            world.addObject(bar, getX()+40, getY()+105);
            spawned=true;
        }
        if (Greenfoot.mouseClicked(this)){
            world.cardUsedOnWorld(); 
        } 
    }
    
    @Override 
    public void die(){
        world.removeObject(this);
        world.removeObject(this.bar);
        dead=true;
    }
    
    public int getStrength(){
        return strength;
    }
    public int getDex(){
        return dex;
    }
    
    public void setWorld(FightWorld fightworld){
        world=fightworld;
    }
    public void setSpawned(boolean spawned){
        this.spawned=spawned;
    }
    /**
     * reset after fight
     */
    public void afterFight(){
        strength=0;
        dex=0;
        demonForm=0;
        metallicize=0;
        barricade=false;
    }
    
}
