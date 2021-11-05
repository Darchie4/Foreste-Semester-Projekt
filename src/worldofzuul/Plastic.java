package worldofzuul;

public class Plastic extends Item implements Placeble{
    private int costOfPickup;

    public Plastic(String name, String description) {
        super(name, description);
        this.costOfPickup = 15;
    }

    public Plastic pickup(Player p) {
        return null;
    }

    public void place(Placeble o) {

    } 
    @Override
    public void print() {

    }


    @Override
    public String toString() {
        return "Plastic{}";
    }
}
