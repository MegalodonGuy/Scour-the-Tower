import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that enemies will inherit from, contains health, max health 
 * and ways to be hit and killed
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    Player player;
    Deck deck;
    protected int ran; // used for attack rolls
    protected String nextMove;
    protected Label intentLabel;
    private FightWorld world;
    private IntentSymbols intentSymbol; 
    public Enemy(int maxHealth, int health,Deck deck, FightWorld world,Player player){
        super(maxHealth, health, deck, world);
        this.player=player; 
        this.deck =deck;
        this.world=world;
    }
    // default, overide in specific enemy, goes through moves in enemy clss
    protected void attackPattern(){
        System.out.println("hey");
    }

    @Override 
    public void act(){
        if (!spawned){
            intentLabel = new Label(""+getNextMove());
            setIntentSymbol();
            world.addObject(intentLabel,getX(),getY()-50);
            world.addObject(intentSymbol,getX(),getY()-100);
        }
        if (Greenfoot.mouseClicked(intentLabel)){
            clicked();
        }
        super.act();
    }
    
    @Override 
    public void die(){
        world.removeObject(this.intentLabel);
        world.removeObject(this.intentSymbol);
        super.die();
    }

    @Override
    public void turnPassed(){
        super.turnPassed();
        ((Enemy)this).attackPattern(); //do attack pattern
        intentLabel.setText(""+getNextMove());
        updateIntentSymbol();
    }

    public void hit(int damage,int vulnerable, int weaken, int attackerWeakend){

        if (Deck.getSelectedCard()==null){
            return;
        }
        Card card = (Card)(Deck.getSelectedCard());
        if (card.getCardID()==8){
            damage=player.getBlock();
            // body slam does dmg = to block
        }
        super.hit(damage, vulnerable, weaken, attackerWeakend);
    }

    public String getNextMove(){
        return nextMove;
        // returns string of this enemies next move
    }
    
    private void setIntentSymbol(){
        switch(getNextMove()){
            case "Dark Strike":
            intentSymbol = new IntentSymbols(new GreenfootImage("SwordIntent.png"));
            break;
            case "Incantation":
            intentSymbol = new IntentSymbols(new GreenfootImage("BuffIntent.png"));
            break;
            case "Activate":
            intentSymbol = new IntentSymbols(new GreenfootImage("SleepingIntent.png"));       
            break;
            case "Divider":
            intentSymbol = new IntentSymbols(new GreenfootImage("HugeAttack.png"));    
            break;
            case "Sear":
            intentSymbol = new IntentSymbols(new GreenfootImage("SmallAttackDebuff.png"));    
            break;
            case "Inflame":
            intentSymbol = new IntentSymbols(new GreenfootImage("BuffIntent.png"));    
            break;
            case "Tackle":
            intentSymbol = new IntentSymbols(new GreenfootImage("SwordIntent.png"));    
            break;
            case "Inferno":
            intentSymbol = new IntentSymbols(new GreenfootImage("BigAttackDebuff.png"));    
            break;
            case "Chomp":
            intentSymbol = new IntentSymbols(new GreenfootImage("SwordIntent.png"));    
            break;
            case "Thrash":
            intentSymbol = new IntentSymbols(new GreenfootImage("BlockAttackIntent.png"));    
            break;
            case "Bellow":
            intentSymbol = new IntentSymbols(new GreenfootImage("BuffIntent.png"));    
            break;
            case "Beam":
            intentSymbol = new IntentSymbols(new GreenfootImage("SmallAttack.png"));    
            break;
            case "Bolt":
            intentSymbol = new IntentSymbols(new GreenfootImage("SmallDebuff.png"));    
            break;
            case "Attack":
            intentSymbol = new IntentSymbols(new GreenfootImage("SwordIntent.png"));    
            break;
            case "Siphon Soul":
            intentSymbol = new IntentSymbols(new GreenfootImage("Debuff.png"));    
            break;
            case "Sleep":
            intentSymbol = new IntentSymbols(new GreenfootImage("SleepingIntent.png"));    
            break;
            
        }
    }
    
    protected void updateIntentSymbol(){
        switch(getNextMove()){
            case "Dark Strike":
            intentSymbol.setImage("SwordIntent.png");  
            break;
            case "Incantaition":
            intentSymbol.setImage("BuffIntent.png");    
            break;
            case "Activate":
            intentSymbol.setImage("SleepingIntent.png");    
            break;
            case "Divider":
            intentSymbol.setImage("HugeAttack.png");  
            break;
            case "Sear":
            intentSymbol.setImage("SmallAttackDebuff.png");  
            break;
            case "Inflame":
            intentSymbol.setImage("BuffIntent.png");    
            break;
            case "Tackle":
            intentSymbol.setImage("SwordIntent.png");  
            break;
            case "Inferno":
            intentSymbol.setImage("BigAttackDebuff.png");  
            break;
            case "Chomp":
            intentSymbol.setImage("SwordIntent.png");     
            break;
            case "Thrash":
            intentSymbol.setImage("BlockAttackIntent.png");  
            break;
            case "Bellow":
            intentSymbol.setImage("BuffIntent.png");    
            break;
            case "Beam":
            intentSymbol.setImage("SmallAttack.png");    
            break;
            case "Bolt":
            intentSymbol.setImage("SmallDebuff.png");    
            break;
            case "Attack":
            intentSymbol.setImage("MediumAttack.png");    
            break;
            case "Siphon Soul":
            intentSymbol.setImage("Debuff.png");     
            break;
            case "Sleep":
            intentSymbol.setImage("SleepingIntent.png");   
            break;
            case "Stunned":
            intentSymbol.setImage("Stunned.png");   
            break;
            
        }
    }

}