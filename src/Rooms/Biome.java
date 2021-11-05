package Rooms;

import Exceptions.GridPlaceFull;
import worldofzuul.Placeble;
import worldofzuul.Plastic;
import worldofzuul.PlasticType;

import java.util.Random;

public class Biome extends Room{
    private int plasticAmount;

    public Biome(String description, int plasticAmount, int gridWidth, int gridHeight) {
        super(description, gridWidth, gridHeight);
        this.plasticAmount = plasticAmount;
    }

/**
 * Check if return value is 0 for winning condition
 * */

    public void generateGrid(){
        int plasticPlaced = 0;
        int x;
        int y;
        int plasticTypeSelected;
        PlasticType[] plasticTypes = PlasticType.values();

        while (this.plasticAmount > plasticPlaced) {
            Random random = new Random();
            x = random.nextInt(getGridWidth());
            y = random.nextInt(getGridHeight());
            plasticTypeSelected = random.nextInt(plasticTypes.length-1);

            try{
                placeOnGrid(x,y,plasticTypes[plasticTypeSelected]);
                plasticPlaced++;
            } catch (GridPlaceFull e){
                continue;
            }
        }
    }

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
