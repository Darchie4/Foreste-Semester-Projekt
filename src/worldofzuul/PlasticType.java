package worldofzuul;

public enum PlasticType
{
    SOFT("soft"), HARD("hard"), PANT("pant");

    private String type;
    PlasticType(String type) { this.type = type;}

    @Override
    public String toString() {
        return type;
    }
}
