package Rooms;

import Exceptions.FullInventoryException;
import Exceptions.OutOfPointsException;
import worldofzuul.Equipment;
import worldofzuul.Game;
import worldofzuul.Inventory;
import worldofzuul.Item;

import java.util.ArrayList;

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

    public void buy (Equipment item) throws FullInventoryException, OutOfPointsException {

        if (Game.player.getPoints() >= (item.getPrice())) {
            Game.player.addItemToInventory(item);
            Game.player.subtractPoints(item.getPrice());
            this.inventory.removeItem(item);
        } else {
            System.out.println("Du har desv\u00E6rre ikke nok point :(");
        }
    }

    public void buyUpgrade(Equipment item) throws FullInventoryException, OutOfPointsException{ //sort out this argument of a method
        if (Game.player.getPoints() >= (item.getPrice())) { //get price of item
            for (Item inventoryItem : Game.player.getInventory().getItems()) { // searches for ALL items in inventory
                if (inventoryItem instanceof Equipment equipment) { //filters for equipment
                    if (equipment.getId() == 1){ // searches for correct ID: but parses in an item

                        Game.player.subtractPoints(equipment.getPrice()); // finds price of equipment
                        equipment.upgrade(); //upgrades equipment

                    }
                }
            }
        }
    }
}