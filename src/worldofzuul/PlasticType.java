package worldofzuul;

public enum PlasticType implements Drawable {
    SOFT( 'B', "Blød"),
    HARD('H', "Hård"),
    PANT('P', "Pant");

    private final char symbol;
    private final String name;
    PlasticType (char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

    public char getSymbol() {
        return symbol;
    }

}
