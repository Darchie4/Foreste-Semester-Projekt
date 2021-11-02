package worldofzuul;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) this.items.clone();
    }
}
