package worldofzuul;

public abstract class Item implements Printable{
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
