package worldofzuul;

public class Biome extends Room{
    private int plasticAmount;

    public Biome(String description, int plasticAmount) {
        super(description);
        this.plasticAmount = plasticAmount;
    }

/**
 * Check if return value is 0 for winning condition
 * */
    public int subtractPlastic(int plastic)
    {
        return --this.plasticAmount;
    }

    public void setPlasticAmount(int plastic)
    {
        this.plasticAmount = plastic;
    }

    public int getPlasticAmount()
    {
        return this.plasticAmount;
    }



}
