package Rooms;

import Exceptions.FullInventoryException;
import worldofzuul.Equipment;
import worldofzuul.Inventory;
import worldofzuul.Item;

import java.util.ArrayList;

public class Shop extends Room {

    private Inventory inventory;

    public Shop(ArrayList<Item> items, String description) throws FullInventoryException //
    {
        super(description);
        this.inventory = new Inventory();
        this.inventory.addItem(items);
    }

    public ArrayList<Item> getAllItems() {
        return inventory.getItems();
    }

    public void buy (Equipment item){

        if (Game.player.points >= (item.getPrice())) {
            Game.player.inventory.addItem(item);
            Game.player.subtractPoint(item.getPrice());
            this.inventory.removeItem(item);
        }
        else
            System.out.println("Du har desværre ikke nok point :(");

    }
}