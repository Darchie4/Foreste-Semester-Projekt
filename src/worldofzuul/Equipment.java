package worldofzuul;

public class Equipment extends Item{

    int modifier;
    int price;

        super(name, description);
    Equipment(int modifier, int price, String description, String name){
        this.modifier = modifier;
        this.price = price;
    }

    public int getModifier() {
        return this.modifier;
    }

    public int getPrice()
    {
        return this.price;
<<<<<<< HEAD
=======
    }
    public Equipment(String name, String description) {
        super(name, description);
>>>>>>> 809f79cd2009984962cd81b28120e358fff2705e
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void print() {
    }
}
