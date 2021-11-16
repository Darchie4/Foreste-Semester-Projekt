package Rooms;

import Exceptions.FullInventoryException;
import Exceptions.OutOfPointsException;
import worldofzuul.Equipment;
import worldofzuul.Game;
import worldofzuul.Inventory;
import worldofzuul.Item;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop extends Room {

    private Inventory inventory;

    public Shop(ArrayList<Item> items, String description) throws FullInventoryException //
    {
        super(description);
        this.inventory = new Inventory(5);
        this.inventory.addItemList(items);
    }

    public ArrayList<Item> getAllItems() {
        return inventory.getItems();
    }

    public void buy (Equipment equipment) throws FullInventoryException, OutOfPointsException {

        if (Game.player.getPoints() >= (equipment.getPrice())) {
            Game.player.addItemToInventory(equipment);
            Game.player.subtractPoints(equipment.getPrice());
            this.inventory.removeItem(equipment);
        } else {
            System.out.println("Du har desv\u00E6rre ikke nok point :(");
        }
    }

    public void buyUpgrade() throws OutOfPointsException{
        Equipment equipment = Game.player.getEquipmentInHand();
        int upgradePrice = equipment.getPrice();
        System.out.println("Det koster " + upgradePrice + " at opgradere " + equipment.getName());
        System.out.println("Vil du gerne opgradere det? Ja eller Nej?");
        String answer = Game.getInput();
        if (answer.toLowerCase().equals("ja") && checkPoints(equipment.getPrice())) {
            Game.player.getEquipmentInHand().upgrade();
        }
    }

    private void printUpgradeMenu(){

    }

    private boolean checkPoints(int price)throws OutOfPointsException{
        try {
            if(Game.player.getPoints() < price)
                throw new OutOfPointsException();
            Game.player.subtractPoints(price);
            return true;
        } catch(OutOfPointsException ex){
            System.out.println("Du har desværre ikke nok penge til at opgradere");
            return false;
        }
    }
}