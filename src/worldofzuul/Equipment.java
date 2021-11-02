package worldofzuul;

public class Equipment extends Item{

    int modifier;
    int price;

    Equipment(int modifier, int price, String description){
        this.description = description;
        this.modifier = modifier;
        this.price = price;
    }

    public int getModifier() {
        return this.modifier;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public void print() {

    }
}
