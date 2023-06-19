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
    public Enemy(int maxHealth, int health,Deck deck, FightWorld world,Player player){
        super(maxHealth, health, deck, world);
        this.player=player; 
        this.deck =deck;
    }
    // default, overide in specific enemy, goes through moves in enemy clss
    protected void attackPattern(){
        System.out.println("hey");
    }

    @Override
    public void turnPassed(){
        super.turnPassed();
        ((Enemy)this).attackPattern(); //do attack pattern

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
    
}