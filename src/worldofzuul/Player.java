package worldofzuul;

public class Player {
    private Inventory inventory;
    private Item itemInHand;
    private int energy;
    private int points;

    public Player() {
        this.inventory = new Inventory();
    }
}
