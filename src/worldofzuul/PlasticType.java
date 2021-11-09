package worldofzuul;

public enum PlasticType implements Placeble {
    SOFT(new Plastic("Blød plast", "Dette er et stykke blød plastik. Det skal sorteres i blød plast"), 'B'),
    HARD(new Plastic("Hård plast","Dette er et stykke hård plastik. Det skal sorteres i hård plast"),'H'),
    PANT(new Plastic("Pant", "Dette er et stykke plastik som har pant på. Det skal i pant maskinen"),'P');

    private final Plastic plast;
    private final char symbol;

    PlasticType(Plastic plast, char symbol) {
        this.plast = plast;
        this.symbol = symbol;
    }

    public Plastic getPlast() {
        return plast;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName(){
        return this.name();
    }

}
