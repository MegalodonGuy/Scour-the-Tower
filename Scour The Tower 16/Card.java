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
    private boolean target; // is it a non target or target card? - assumes random target for attacking
    private boolean aoe; // does it effect every enemy? (or every enemy + yourself)
    private int attackNum; // amount of attacks(multi attacks)
    private boolean exhaust; // goes to exhaust pile instead of discard pile
    private boolean power;
    private boolean ethereal; // if card isn't used, exhaust
    GreenfootImage image; 
    public Card(int CardID){
        this.CardID = CardID;
        
        attackNum=1;// by default 
        aoe=false;
        target=true;
        exhaust= false;
        power=false;
        ethereal=false;
        switch (CardID){
            case 1: 
            image = new GreenfootImage("Strike.png"); 
            setStats(6,0,1); //attack damage, block amount and energy cost
            break;
            case 2:
            image = new GreenfootImage("Defend.png");
            setStats(0,5,1);
            target=false;
            break;
            case 3:
            image = new GreenfootImage("Bash.png");
            setStats(8,0,2);
            vulnerable=2;  
            break;
            case 4:
            image = new GreenfootImage("Cleave.png");
            setStats(8,0,1);
            aoe=true; 
            break;
            case 5:
            image = new GreenfootImage("Pummel.png");
            setStats(2,0,1);
            attackNum=4;
            exhaust=true;
            break;
            case 6:
            image = new GreenfootImage("SwordBoomerang.png");
            setStats(3,0,1);
            attackNum=3;
            target=false;
            break;
            case 7:
            image = new GreenfootImage("Clothesline.png");
            setStats(12,0,2);  
            weaken=2;
            break;
            case 8:
            image = new GreenfootImage("BodySlam.png");
            setStats(0,0,1);  
            break;
            case 9:
            image = new GreenfootImage("FiendFire.png");
            setStats(7,0,2); 
            exhaust=true;
            break;
            case 10:
            image = new GreenfootImage("ShrugItOff.png");
            setStats(0,8,1); 
            target=false;
            break;
            case 11:
            image = new GreenfootImage("IronWave.png");
            setStats(5,5,1); 
            break;
            case 12:
            image = new GreenfootImage("PommelStrike.png");
            setStats(9,0,1); 
            break;
            case 13:
            image = new GreenfootImage("Thunderclap.png");
            setStats(4,0,1); 
            vulnerable=1;
            aoe=true;
            break;
            case 14:
            image = new GreenfootImage("TwinStrike.png");
            setStats(5,0,1); 
            attackNum=2;
            break;
            case 15:
            image = new GreenfootImage("Hemokinesis.png");
            setStats(15,0,1); 
            break;
            case 16:
            image = new GreenfootImage("Bludgeon.png");
            setStats(32,0,3);
            break;
            case 17: 
            image = new GreenfootImage("Disarm.png");
            setStats(0,0,1);
            exhaust=true;
            break;
            case 18: 
            image = new GreenfootImage("Inflame.png");
            setStats(0,0,1);
            target=false;
            power=true;
            break;
            case 19: 
            image = new GreenfootImage("Reaper.png");
            setStats(4,0,2);
            aoe=true;
            break;
            case 20: 
            image = new GreenfootImage("Feed.png");
            setStats(10,0,1);
            exhaust=true;
            break;
            case 21: 
            image = new GreenfootImage("Uppercut.png");
            setStats(13,0,2);
            weaken=1;
            vulnerable=1;
            break;
            case 22: 
            image = new GreenfootImage("Intimidate.png");
            setStats(0,0,0);
            weaken=1;
            aoe=true;
            exhaust=true;
            break;
            case 23: 
            image = new GreenfootImage("Shockwave.png");
            setStats(0,0,2);
            weaken=3;
            vulnerable=3;
            aoe=true;
            exhaust=true;
            break;
            case 24: 
            image = new GreenfootImage("Impervious.png");
            setStats(0,30,2);
            target=false;
            exhaust=true;
            break;
            case 25: 
            image = new GreenfootImage("Offering.png");
            setStats(0,0,0);
            target=false;
            exhaust=true;
            break;
            case 26: 
            image = new GreenfootImage("SeeingRed.png");
            setStats(0,0,1);
            target=false;
            exhaust=true;
            break;
            case 27: 
            image = new GreenfootImage("Bloodletting.png");
            setStats(0,0,0);
            target=false;
            break;
            case 28: 
            image = new GreenfootImage("Sentinel.png");
            setStats(0,5,1);
            target=false;
            break;
            case 29: 
            image = new GreenfootImage("LimitBreak.png");
            setStats(0,0,1);
            target=false;
            exhaust=true;
            break;
            case 30: 
            image = new GreenfootImage("Entrench.png");
            setStats(0,0,2);
            target=false;
            break;
            case 31: 
            image = new GreenfootImage("Carnage.png");
            setStats(20,0,2);
            ethereal=true;
            break;
            case 32: 
            image = new GreenfootImage("GhostlyArmor.png");
            setStats(0,10,1);
            ethereal=true;
            target=false;
            break;
            case 33: 
            image = new GreenfootImage("Dropkick.png");
            setStats(5,0,1);
            break;
            case 34: 
            image = new GreenfootImage("HeavyBlade.png");
            setStats(14,0,2);
            break;
            case 35: 
            image = new GreenfootImage("Immolate.png");
            setStats(21,0,2);
            aoe=true;
            break;
            case 36: 
            image = new GreenfootImage("Burn.png");
            setStats(0,0,999999999);
            break;
            case 37: 
            image = new GreenfootImage("Anger.png");
            setStats(6,0,0);
            break;
            case 38: 
            image = new GreenfootImage("PowerThrough.png");
            setStats(0,15,1);
            target=false;
            break;
            case 39: 
            image = new GreenfootImage("Wound.png");
            setStats(0,0,999999999);
            break;
            case 40: 
            image = new GreenfootImage("WildStrike.png");
            setStats(12,0,1);
            break;
            case 41: 
            image = new GreenfootImage("TrueGrit.png");
            setStats(0,7,1);
            target=false;
            break;
            case 42: 
            image = new GreenfootImage("RecklessCharge.png");
            setStats(7,0,0);
            break;
            case 43: 
            image = new GreenfootImage("Dazed.png");
            setStats(0,0,999999999);
            ethereal=true;
            break;
            case 44: 
            image = new GreenfootImage("BattleTrance.png");
            setStats(0,0,0);
            target=false;
            break;
            case 45: 
            image = new GreenfootImage("Barricade.png");
            setStats(0,0,3);
            target=false;
            break;
            case 46: 
            image = new GreenfootImage("DemonForm.png");
            setStats(0,0,3);
            target=false;
            break;
        }
        image.scale(150,194);
        setImage(image); 
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
             
             setLocation(getX(), getY()-100);
            }
            else{ 
             Deck.setSelected(null);
             //setLocation(getX(), getY()+100);
            }
            }
        if (this==Deck.getSelectedCard()){
            selected=true;
        }
        else{
            selected=false;
        }
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
    public boolean getAOE(){
        return aoe; 
    }
    public int getAttackNum(){
        return attackNum; 
    }
    public boolean getExhaust(){
        return exhaust; 
    }
    public boolean getEthereal(){
        return ethereal; 
    }
    public boolean getPower(){
        return power; 
    }
}
