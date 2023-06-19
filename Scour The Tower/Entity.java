import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (Ben H.) 
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
    protected int strength=0; // added dmg on to every attack
    protected int dex=0; // added block on every block 

    private FightWorld world;
    protected Bar bar;
    protected Label label;
    //powers
    protected boolean barricade=false;
    protected int demonForm=0;
    protected int metallicize=0;
    private int incantation=0;

    protected boolean spawned = false;

    public Entity(int maxHealth, int health,Deck deck, FightWorld world){
        this.maxHealth=maxHealth;
        this.health=health;
        this.deck=deck;
        dead=false;
        this.world =world; 
        bar = new Bar(this);
        label = new Label(health + "/" + maxHealth);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (!spawned){
            world.addObject(bar, getX(), getY()+125);
            world.addObject(label, getX(), getY()+125);
            spawned=true;
        }
        if (Greenfoot.mouseClicked(this)){
            clicked();
        } 

    }
    
    /**
     * registered when player clicks on enemy, tries to use selected card on it
     */
    public void clicked(){
        // if card used on character
        if (Deck.getSelectedCard()!=null){
            Card card = (Card)Deck.getSelectedCard();
            if (card.getEnergy()>deck.getAvailableEnergy()){
                return; 
            }
            int attackNum=card.getAttackNum();
            int strengthFactor=1;

            //cards with special effects
            if (card.getCardID()==9){
                attackNum=deck.getHand().size()-1;
            }
            else if (card.getCardID()==12){
                deck.drawRandom();
                deck.drawRandom();
            }
            else if (card.getCardID()==15){
                world.getPlayer().takeStaticDamage(2); // dont want other effects to take place
            }
            else if (card.getCardID()==17){
                this.increaseStrength(-2); 
            }
            else if (card.getCardID()==33 && vulnerable>0){
                deck.drawRandom();
                deck.gainEnergy(1);
            }
            else if (card.getCardID()==34){
                strengthFactor=3;
            }
            else if (card.getCardID()==37){
                Card cardCopy = new Card(37); // supposed to be direct copy but I cant be arsed
                deck.addIntoDiscardPile(cardCopy);
            }
            else if (card.getCardID()==40){ 
                deck.addIntoDrawPile(new Card(39));
            }
            else if (card.getCardID()==42){ 
                deck.addIntoDrawPile(new Card(43));
            }

            int tempStrength=world.getPlayer().getStrength();
            if (card.getDamage()==0){ // so cards that dont attack aren't effected by strength
                tempStrength=0; 
            }

            //if it is a target card use it on this
            if (card.getTarget()){
                for (int i=0; i<attackNum; i++){
                    hit(card.getDamage()+tempStrength*strengthFactor,card.getVulnerable(),card.getWeaken(),world.getPlayer().getWeaken()); 
                    if (card.getCardID()==20&&this.dead){
                        world.getPlayer().increaseMaxHealth(3);
                    }
                    world.getPlayer().block(card.getBlock());
                }
                deck.playedCard(card);
            }
            else{
                //use it on world
                world.cardUsedOnWorld(); 
            }

            //fiend fire
            if (card.getCardID()==9){
                deck.exhaustHand(card);
            }
        }
    }

    /**
     * hit for damage, apply vulnerable and weaken (usually 0), 
     * if attacker is weakend dmg foes down if this is vulnerable dmg goes up
     */
    public void hit(int damage,int vulnerable, int weaken, int attackerWeakend){
        double dmgMod=1; 
        if (this.vulnerable>0){
            dmgMod*=1.5; 
        }
        if (attackerWeakend>0){
            dmgMod*=0.75; 
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
        label.setText(health + "/" + maxHealth);

        this.vulnerable+=vulnerable; 
        this.weakened+=weaken;
        if (health<=0 && !dead){
            die(); 
        }

    }

    /**
     * heal for certain amount of health, cannot go above max
     */
    public void heal (int health){ 
        this.health+=health;
        if (this.health>this.maxHealth){ 
            this.health=this.maxHealth;
        }
        label.setText(this.health + "/" + maxHealth);
    }

    /**
     * deal damage ignoring various effects
     */
    public void takeStaticDamage(int dmg){
        this.health-=dmg;
        if (health<=0 && !dead){
            die();
        }
        label.setText(health + "/" + maxHealth);
    }

    public void reduceMaxHealth(int reduction){
        maxHealth-=reduction;
        if (health>maxHealth){ 
            health=maxHealth;
        }
        label.setText(health + "/" + maxHealth);
    }

    public void increaseMaxHealth(int promotion){
        reduceMaxHealth(-promotion); 
        label.setText(health + "/" + maxHealth);
    }

    /**
     * block takes away the damagae that would have been done without it = to itslef
     */
    public void block (int block){
        if (block==0){
            return;
        }
        this.block+=(block+dex); 
    }

    public void vulnerable(int vulnerable){
        this.vulnerable+=vulnerable; 
    }

    public void weaken(int weaken){
        this.weakened+=weaken; 
    }

    public void increaseStrength(int strength){
        this.strength+=strength;
    }

    public void increaseDex(int dex){
        this.dex+=dex;
    }

    public int getWeaken(){
        return this.weakened; 
    }

    public boolean getDead(){
        return this.dead;
    }

    public void die(){
        getWorld().removeObject(this);
        world.removeObject(this.bar);
        world.removeObject(this.label);
        dead=true; 
    }

    public int getBlock(){
        return block;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    /**
     * when turn passed block resets, statuses you have go down and powers do their thing
     */
    public void turnPassed(){
        if (!barricade){
            this.block=0;
        }
        if (vulnerable>0){
            vulnerable--; 
        }
        if (weakened>0){
            weakened--; 
        } 

        strength+=2*demonForm;
        strength+=incantation;
        block+=metallicize;
    }

    //powers

    public void barricade(){
        barricade=true;
    }

    public void demonForm(){
        demonForm+=1;
    }

    public void incantation(int amount){
        incantation+=amount;
    }

    public void metallicize(int amount){
        metallicize+=amount;
        block(amount);
    }
}
