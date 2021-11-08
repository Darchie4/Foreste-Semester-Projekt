package worldofzuul;

public enum PlasticType implements Placeble {
    SOFT(new Plastic("Blød plast", "Dette er et stykke blød plastik. Det skal sorteres i blød plast"), 'B'), HARD(new Plastic("Hård plast","Dette er et stykke hård plastik. Det skal sorteres i hård plast"),'H'), PANT(new Plastic("Pant", "Dette er et stykke plastik som har pant på. Det skal i pant maskinen"),'P');

<<<<<<< HEAD
    private String type;
    PlasticType(String type) { this.type = type;}

    @Override
    public String toString() {
        return type;
=======
    private Plastic plast;
    private char symbol;

    PlasticType(Plastic plast, char symbol) {
        this.plast = plast;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
>>>>>>> 0c40e200b48f17a100dca3460293fed5ec5daa11
    }
}
