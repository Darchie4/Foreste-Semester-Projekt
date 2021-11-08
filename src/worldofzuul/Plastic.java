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

<<<<<<< HEAD
    public PlasticType getType() {
        return type;
=======
    @Override
    public char getSymbol() {
        return '<';
    }

    @Override
    public String toString() {
        return "Plastic{}";
>>>>>>> 0c40e200b48f17a100dca3460293fed5ec5daa11
    }
}
