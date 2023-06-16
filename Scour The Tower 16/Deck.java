import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class Deck here.
 * 
 * @author (Ben H.) 
 * @version (a version number or a date)
 */
public class Deck extends Actor
{
    private ArrayList<Object> deck = new ArrayList<Object>(); //full deck
    private ArrayList<Object> hand = new ArrayList<Object>(); // current hand
    private ArrayList<Object> drawPile = new ArrayList<Object>(); // draw from here
    private ArrayList<Object> discardPile = new ArrayList<Object>(); // cards go here after used or turn ended
    private ArrayList<Object> exhaustPile = new ArrayList<Object>();// exhaust cards go here so they cannot reappear during the current fight after played
    private static Object selectedCard; // card selected to be used, one at a time
    private int energy; // total energy, will deplete from card cost (mana)
    private int maxEnergy;
    public Deck(ArrayList<Object> deck){
        this.deck=Util.cloneContents(deck); 
        drawPile=Util.cloneContents(deck); 
        maxEnergy=3; 
        energy=maxEnergy;
        deal();
    }

    /**
     * Act - do whatever the Deck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {

    } 

    public void drawRandom(){
        if (drawPile.size()==0){
            drawPile=Util.cloneContents(discardPile); 
            discardPile.clear(); 
        }
        Collections.shuffle(drawPile);
        hand.add(drawPile.get(0)); 
        drawPile.remove(0); 
    }

    public void deal(){
        discardHand(); 
        for (int i=0; i<5; i++){
            drawRandom(); 
        }
    } 

    public void discardHand(){ 
        for (int i=0; i<hand.size(); i++){
            Card card = (Card)hand.get(i);
            if (((Card)hand.get(i)).getEthereal()){
                hand.remove(card);
                exhaustCard(card); 
            }
            else{
                getWorld().removeObject(card); // clears card off world so it can be reused
                card.deselect();
                setSelected(null);
                discardPile.add(card);
            }
        }
        hand.clear(); 
        energy=maxEnergy; 
    }

    public void exhaustHand(){
        for (int x=0; x<hand.size(); x++){
            exhaustCard((Card)hand.get(x));
        }
        hand.clear();
    }

    public void exhaustCard(Card card){
        if (card.getCardID()==28){
            energy+=2;
        }
        exhaustPile.add(card);
        getWorld().removeObject(card);
    }

    public void discardCard(Object card){
        Card usedCard = (Card)card;
        if (usedCard.getExhaust()){
            exhaustCard(usedCard); 
        }
        else if(usedCard.getPower()){
            //dont put it anywhere, power is used
        }
        else{
            discardPile.add(usedCard); 
        }
        getWorld().removeObject(usedCard);// clears card off world so it can be reused
        hand.remove(usedCard); 
        usedCard.deselect();  
        setSelected(null);
    }

    public void playedCard(Object card){
        this.energy-=((Card)card).getEnergy(); 
        discardCard(card); 
    }

    public ArrayList<Object> getHand(){
        return hand;
    }

    public static Object getSelectedCard(){
        return selectedCard; 
    }

    public static void setSelected(Object card){
        selectedCard=card; 
    }

    public int getAvailableEnergy(){
        return energy; 
    }

    public int getMaxEnergy(){
        return maxEnergy; 
    }

    public void gainEnergy(int energy){
        this.energy+=energy;
        //can go above max just like in game
    }
}
