import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Energy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Energy extends Actor
{
    //Declaring variables for the energy
    //Also declaring a deck so that we can reference the actual deck below
    public int energy;
    public int maxEnergy;
    private Deck deck;
    /**
     * Act - do whatever the Energy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Energy(int maxE, int StartEnergy, Deck deck){
        //Sets values for our variables
        //As well as setting up deck so we can reference it's methods
        this.energy=StartEnergy;
        this.deck=deck;
        this.maxEnergy=maxE;
    }
    public void act()
    {
        //This is what setting up the deck was for
        //We can now use the .getAvailableEnergy method to check the energy
        //If the energy level changes the following happens:
        if (energy != deck.getAvailableEnergy()){
            //The stored energy value in this actor changes as well
            energy = deck.getAvailableEnergy();
            //We update the text on the energyLabel
            FightWorld.energyLabel.setText(energy + "/" + maxEnergy);
        }
    }
    
    
}
