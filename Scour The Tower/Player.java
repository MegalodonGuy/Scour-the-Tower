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
            world.addObject(label, getX()+40, getY()+105);
            world.addObject(blockSymbol, getX()+100, getY()+105);
            world.addObject(blockLabel, getX()+100, getY()+105);
            spawned=true;
        }
        if (Greenfoot.mouseClicked(this)){
            world.cardUsedOnWorld(); 
        } 
    }
    
    @Override 
    public void die(){
        //Code for when the PLAYER dies
        //Update and scale image to dead iron clad
        image = new GreenfootImage("IronCladDead.png");
        image.scale(350,134);
        setImage(image);
        setLocation(275,500);
        
        //Remove battle associated things like hp bar, etc..
        world.removeObject(this.bar);
        world.removeObject(this.label);
        world.removeObject(this.blockSymbol);
        world.removeObject(this.blockLabel);
        
        dead=true; 
        
        //Creat death world, passing through the player and current enemy
        Greenfoot.setWorld(new DeathWorld(this, world.getEnemy()));
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
        vulnerable=0;
        weakened=0;
        block=0;
        blockLabel.setText(""+this.block);
        barricade=false;
    }
    
}
