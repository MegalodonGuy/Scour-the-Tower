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
    public Enemy(int maxHealth, int health,Deck deck, FightWorld world,Player player){
        super(maxHealth, health, deck, world);
        this.player=player; 
        this.deck =deck;
    }
    // default, overide in specific enemy 
    protected void attackPattern(){
        System.out.println("hey");
    }

    @Override
    public void turnPassed(){
        super.turnPassed();
        ((Enemy)this).attackPattern(); 

    }

    public void hit(int damage,int vulnerable, int weaken, int attackerWeakend){

        if (Deck.getSelectedCard()==null){
            return;
        }
        Card card = (Card)(Deck.getSelectedCard());
        if (card.getCardID()==8){
            damage=player.getBlock();
        }
 
        super.hit(damage, vulnerable, weaken, attackerWeakend);

    }
}