package worldofzuul;



public class Plastic extends Item implements Placeble{
    private int costOfPickup;
    private PlasticType type;

    public Plastic(String name, String description, PlasticType type) {
        super(name, description);
        this.costOfPickup = 15;
        this.type = type;
    }

    @Override
    public Plastic pickup(Player p) {
        return null;
    }

    @Override
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
