package worldofzuul;

public class Plastic extends Item{
    private int costOfPickup;

    public Plastic(String name, String description) {
        super(name, description);
        this.costOfPickup = 15;
    }

    public Plastic pickup(Player p) {
        return null;
    }


    @Override
    public void print() {

    }
}
