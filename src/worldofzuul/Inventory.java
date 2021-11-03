package worldofzuul;

import Exceptions.FullInventoryException;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> items;
    int inventorySize;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) throws FullInventoryException {
        if (items.size()+1 > inventorySize){
            throw new FullInventoryException();
        }
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) this.items.clone();
    }

    public ArrayList<Item> getItemsCloned() {
        return (ArrayList<Item>) this.items.clone();
    }
}
