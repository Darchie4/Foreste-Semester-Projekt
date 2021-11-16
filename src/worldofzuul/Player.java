package worldofzuul;

import Exceptions.*;

public class Player implements Drawable {
    private Inventory inventory;
    private Equipment equipmentInHand;
    private int energy;
    private int points;
    private int maxEnergy;

    public Player() {
        this.inventory = new Inventory(10);
        this.equipmentInHand = null;
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

    public Equipment getEquipmentInHand() {
        return this.equipmentInHand;
    }

    public void addEquipmentToHand(Equipment equipment) throws FullHandException {
        if (this.equipmentInHand == null) {
            this.equipmentInHand = equipment;
        } else {
            throw new FullHandException();
        }
    }

    public Item removeEquipmentFromHand() throws EmptyHandException{
        if (this.equipmentInHand != null){
            Item tempItem = this.equipmentInHand;
            this.equipmentInHand = null;
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
        Equipment tempEquipment = getEquipmentInHand();
        try {
            this.addItemToInventory(this.removeEquipmentFromHand());
        } catch (FullInventoryException e){
            System.out.println("Dit inventory er fyldt!");
            try{
                this.addEquipmentToHand(tempEquipment);
            } catch (FullHandException fullhand){
                System.out.println(fullhand.getStackTrace());
            }
        } catch (EmptyHandException e){
            System.out.println("Du har ikke noget i hånden!");
        }
    }

    public void fromInventoryToHand(Equipment equipment){
        try {
            this.addEquipmentToHand(equipment);
        } catch (FullHandException e){
            System.out.println("Du har allerede noget i hånden");
            return;
        }
        this.removeItemFromInventory(equipment);
    }

    public boolean hasSpaceInInventory(){
        return !(inventory.inventorySize == inventory.getItems().size());
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
