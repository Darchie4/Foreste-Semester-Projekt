package worldofzuul;

public enum PlasticType implements Placeble {
    SOFT(new Plastic("Blød plast", "Dette er et stykke blød plastik. Det skal sorteres i blød plast"), '*'), HARD(new Plastic("Hård plast","Dette er et stykke hård plastik. Det skal sorteres i hård plast"),'#'), PANT(new Plastic("Pant", "Dette er et stykke plastik som har pant på. Det skal i pant maskinen"),'@');

    private Plastic plast;
    private char symbol;

    PlasticType(Plastic plast, char symbol) {
        this.plast = plast;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
