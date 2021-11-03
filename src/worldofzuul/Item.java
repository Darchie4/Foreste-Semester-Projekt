package worldofzuul;

public abstract class Item implements Printable{
    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
