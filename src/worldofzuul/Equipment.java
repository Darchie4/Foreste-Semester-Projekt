package worldofzuul;

public class Equipment extends Item{

    private int modifier;
    private int price;
    private int id;
    private int level;

    Equipment(int modifier, int price, String description, String name, int id){
        super(name, description);
        this.modifier = modifier;
        this.price = price;
        this.id = id;
        this.level = 0;
    }

    public int getModifier() {
        return this.modifier;
    }

    public int getPrice()
    {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public void upgrade(){
        level++;
        modifier+=2;
        price+=15;
    }
    
    public Equipment(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void print() {
    }
}
