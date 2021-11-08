package worldofzuul;

import Exceptions.*;

public class Player implements Placeble{
    private Inventory inventory;
    private Item itemInHand;
    private int energy;
    private int points;
    private int maxEnergy;

    public Player() {
        this.inventory = new Inventory(10);
        this.itemInHand = null;
        this.maxEnergy = 100;
        this.energy = maxEnergy;
        this.points = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }
    //////////////////////////
    //// Under here is    ////
    //// Points handeling ////
    //////////////////////////

    public int getPoints(){
        return this.points;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void subtractPoints(int points) throws OutOfPointsException {
        this.points -= points;
        if (this.points < 0) {
            throw new OutOfPointsException();
        }
    }

    //////////////////////////
    //// Under here is    ////
    //// Energy handeling ////
    //////////////////////////


    public int getEnergy() {
        return energy;
    }

    public void addToMaxEnergy(int energy){
        this.maxEnergy += energy;
    }

    public void setEnergyToMax(){
        this.energy = this.maxEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void modifyEnergy(int energyAmount, boolean operator) throws EnergyOutOfBoundsException {
        // Operator values meaning:
        //      true = add
        //      false = subtract

        if (operator){
            this.energy += energyAmount;
        } else {
            this.energy -= energyAmount;
        }

        if (this.energy < 0) {
            throw new OutOfEnergyException();
        } else if (this.energy > this.maxEnergy){
            this.energy = this.maxEnergy;
        }
    }

    ////////////////////////////
    //// Under here is Item ////
    //// in hand handeling  ////
    ////////////////////////////

    public Item getItemInHand() {
        return itemInHand;
    }

    public void addItemToHand(Item item) throws FullHandException {
        if (this.itemInHand == null) {
            this.itemInHand = item;
        } else {
            throw new FullHandException();
        }
    }

    public Item removeItemFromHand() throws EmptyHandException{
        if (this.itemInHand != null){
            Item tempItem = this.itemInHand;
            this.itemInHand = null;
            return tempItem;
        } else {
            throw new EmptyHandException();
        }
    }

    ////////////////////////////////
    //// Under here is Item in ////
    //// inventory handeling   ////
    ///////////////////////////////

    public void addItemToInventory(Item item) throws FullInventoryException {
        this.inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item){
        this.inventory.removeItem(item);
    }

    public void fromHandToInventory(){
        Item tempitem = getItemInHand();
        try {
            this.addItemToInventory(this.removeItemFromHand());
        } catch (FullInventoryException e){
            System.out.println("Dit inventory er fyldt!");
            try{
                this.addItemToHand(tempitem);
            } catch (FullHandException fullhand){
                System.out.println(fullhand.getStackTrace());
            }
        } catch (EmptyHandException e){
            System.out.println("Du har ikke noget i hånden!");
        }
    }

    public void fromInventoryToHand(Item item){
        try {
            this.addItemToHand(item);
        } catch (FullHandException e){
            System.out.println("Du har allerede noget i hånden");
            return;
        }
        this.removeItemFromInventory(item);
    }

    ///////////////////////////////////
    //// Nono don't touch me there ////
    //// This is my nono square    ////
    ///////////////////////////////////


    @Override
    public char getSymbol() {
        return '\uDC64';
    }
}
