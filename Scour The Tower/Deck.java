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
    
    private boolean battleTrance=false;
    public Deck(ArrayList<Object> deck){
        this.deck=Util.cloneContents(deck); 
        drawPile=Util.cloneContents(deck); 
        maxEnergy=3; 
        energy=maxEnergy;
        deal();
    }
    
    public void reset(){
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
        if (battleTrance){
            return;
        }
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
        battleTrance=false;
        for (int i=0; i<5; i++){
            drawRandom(); 
        }
    } 

    public void discardHand(){ 
        int cardAmount=hand.size();
        for (int i=0; i<cardAmount; i++){
            Card card = (Card)hand.get(0);
            if (card.getCardID()==36 && !card.getUpgraded()){
                ((FightWorld)getWorld()).getPlayer().hit(2,0,0,0); // burn hits block
            }
            else if (card.getCardID()==36 && card.getUpgraded()){
                ((FightWorld)getWorld()).getPlayer().hit(4,0,0,0); // burn hits block
            }
            
            if (card.getEthereal()){
                exhaustCard(card);
            }
            else{
                discardPile.add(card);
                getWorld().removeObject(card);// clears card off world so it can be reused
            }
            hand.remove(card); 
            card.deselect();
            setSelected(null);

        }

        energy=maxEnergy; 
    }

    public void exhaustHand(Card card){
        while(hand.size()>0){
              exhaustCard((Card)hand.get(0));
        }
        setSelected(null);
    }

    public void exhaustCard(Card card){
        if (card.getCardID()==28){
            energy+=2;
        }
        exhaustPile.add(card);
        hand.remove(card); 
        getWorld().removeObject(card);
    }

    public void discardCard(Object card){
        Card usedCard = (Card)card;
        if (usedCard.getCardID()==36){
            ((FightWorld)getWorld()).getPlayer().hit(2,0,0,0);
        }
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
    
    public void addIntoDiscardPile(Card card){
        discardPile.add(card);
    }
    public void addIntoHand(Card card){
        hand.add(card);
    }
    public void addIntoDrawPile(Card card){
        drawPile.add(card);
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
    public void battleTrance(){
        battleTrance=true;
    }

    public int getMaxEnergy(){
        return maxEnergy; 
    }

    public void gainEnergy(int energy){
        this.energy+=energy;
        //can go above max just like in game
    }
    
    public void upgradeAllBurns(){ // for hexaghost
        for (int x=0; x<hand.size(); x++){
            if (((Card)hand.get(x)).getCardID()==36){
                ((Card)hand.get(x)).upgrade();
            }
        }
        for (int x=0; x<discardPile.size(); x++){
            if (((Card)discardPile.get(x)).getCardID()==36){
                ((Card)discardPile.get(x)).upgrade();
            }
        }
        for (int x=0; x<drawPile.size(); x++){
            if (((Card)drawPile.get(x)).getCardID()==36){
                ((Card)drawPile.get(x)).upgrade();
            }
        }
        for (int x=0; x<exhaustPile.size(); x++){
            if (((Card)exhaustPile.get(x)).getCardID()==36){
                ((Card)exhaustPile.get(x)).upgrade();
            }
        }
    }
    
    public void setEnergy(int num){
        energy=num;
    }
}
