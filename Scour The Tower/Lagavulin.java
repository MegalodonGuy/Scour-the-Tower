import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lagavulin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lagavulin extends Enemy
{
    private FightWorld world;
    GreenfootImage image;
    private int patternNum=-2;
    private boolean asleep=true;
    public Lagavulin(int health, int maxHealth,Deck deck, FightWorld world, Player player){
        super(maxHealth, health, deck, world,player);
        this.world=world;
        image = new GreenfootImage("Sleep.png");
        image.scale(200,200);
        setImage(image);
        nextMove="Sleep";
        ran=0;
        metallicize(8);
    } 

    @Override
    protected void attackPattern(){
        if (dead){
            return; 
        }
    
        // if it is attacked wake it up and it waits a turn to attack, if 3 moves go by it will wake up and attack immediatly
        //when it is awake it attacks twice then debuffs
        switch (patternNum){
            case -2:
                move1();
                patternNum++;
                break;
            case -1:
                move1();
                patternNum=0;
            case 0:
                move2();
                patternNum++;
                break;
            case 1:
                asleep=false;
                wakeUp();
                move3();
                patternNum++;
                break;
            case 2:
                move3();
                patternNum++;
                break;
            case 3:
                move4();
                patternNum=1;
                break;
        }

        nextMove();
    }

    protected void move1(){ 
        //System.out.println("Sleep");
    }

    protected void move2(){ 
        //System.out.println("Stunned");
    }

    protected void move3(){
        player.hit(18+strength,0,0,weakened);
        //System.out.println("Attack");
    }

    protected void move4(){
        player.increaseDex(-1);
        player.increaseStrength(-1);
        //System.out.println("Tackle");
    }
    
    /**
     * if it is hit then it wakes up early, it has to do damage so it must break its block and do atleast 1 damage
     */
    @Override
    public void hit(int damage,int vulnerable, int weaken, int attackerWeakend){
        super.hit(damage,vulnerable,weaken,attackerWeakend);
        if (health==maxHealth){
            return;
        }
        if (asleep){
            patternNum=0;
            asleep=false;
            wakeUp();
        }
    }
    

    private void wakeUp(){
        image = new GreenfootImage("Lagavulin.png");
        image.scale(200,200);
        setImage(image);
        patternNum=0;
        nextMove();
        updateIntentSymbol();
        intentLabel.setText(""+getNextMove());
    }

    public void nextMove(){
        if (asleep){
            nextMove="Sleep";
        }
        switch (patternNum){
            case 0:
                nextMove="Stunned";
                break;
            case 1:
                nextMove="Attack";
                break;
            case 2:
                nextMove="Attack";
                break;
            case 3:
                nextMove="Siphon Soul";
                break;
        }
    }
}
