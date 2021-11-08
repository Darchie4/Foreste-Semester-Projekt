package worldofzuul;

import Exceptions.FullInventoryException;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> items;
    int inventorySize;

    public Inventory(int inventorySize) {
        this.items = new ArrayList<>();
        this.inventorySize = inventorySize;
    }

    public void addItem(Item item) throws FullInventoryException {
        if (items.size()+1 > inventorySize){
            throw new FullInventoryException();
        }
        this.items.add(item);
    }
    public void addItemList(ArrayList<Item> item) throws FullInventoryException {
        if (items.size()+1 > inventorySize){
            throw new FullInventoryException();
        }
        this.items.addAll(item);
    }

    public Item searchItemName (String itemName){
        for (Item item: this.items) {
            if (item.getName().equals(itemName))
                return item;
        }
        System.out.println("Det har du ikke p\u00E5 dig");
        return null;
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public ArrayList<Item> getItems() {return (ArrayList<Item>) this.items.clone();}

}
