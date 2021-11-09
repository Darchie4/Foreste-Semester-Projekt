package worldofzuul;

public class Plastic extends Item{
    private final int costOfPickup = 15;
    private final PlasticType plasticType;

    private Plastic(String name, String description, PlasticType plasticType) {
        super(name, description);
        this.plasticType = plasticType;
    }



    public Plastic pickup(Player p) {
        return null;
    }


    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        StringBuilder tempString = new StringBuilder();
        tempString.append(this.getDescription());

        String costOfPickupToString = "\nDet koster \"" + this.costOfPickup +"\" energi at samle op.";
        tempString.append(costOfPickupToString);

        String nameAndSymbol = "\n" + this.getName() + " bliver vist på kortet med bogstavet: " + getPlasticType().getSymbol();
        tempString.append(nameAndSymbol);

        return tempString.toString();
    }

    public PlasticType getPlasticType() {
        return this.plasticType;
    }

    public static Plastic plasticFactory(PlasticType plasticType){
        switch(plasticType){
            case HARD -> {
                return new Plastic("Hård plastik",
                        "Dette er et stykke hård plastik. Det skal sorteres i hård plastik containeren.", PlasticType.HARD);
            }
            case SOFT -> {
                return new Plastic("Blød plastik",
                        "Dette er et stykke blød plastik. Det skal sorteres i blød plastik containeren.", PlasticType.SOFT);
            }
            case PANT -> {
                return new Plastic("Pant",
                        "Dette er et stykke pant. Det skal sorteres i pantautomaten.", PlasticType.PANT);
            }
            default -> {
                System.out.println("Plastic wasn't created: Wrong type");
                return null;
            }
        }
    }
}
