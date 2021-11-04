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
        this.inventory.addItem(items);
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
            System.out.println("Du har desværre ikke nok point :(");
        }

    }
}