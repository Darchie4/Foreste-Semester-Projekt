package Rooms;

import Exceptions.GridPlaceFull;
import worldofzuul.PlasticType;
import worldofzuul.RecervedGridPlace;

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
        String[] exitsString = this.getDirections().keySet().toArray(new String[0]);
        int plasticPlaced = 0;
        int x;
        int y;
        int plasticTypeSelected;
        PlasticType[] plasticTypes = PlasticType.values();

        for (String s : exitsString) {
            switch (s) {
                case "North" :
                    try{
                        placeOnGrid(0,getGridHeight()/2,this.getExits().get(this.getDirections().get(s)));

                        placeOnGrid(0, getGridHeight()/2+1,new RecervedGridPlace());
                        placeOnGrid(0,(getGridHeight()/2)-1,new RecervedGridPlace());
                        placeOnGrid(1,getGridHeight()/2,new RecervedGridPlace());

                    } catch (GridPlaceFull e){
                        System.out.println("Der skete en fejl under generationen af kortet");
                    }
                    break;
                case "South" :
                    try {
                        placeOnGrid(getGridWidth()-1,getGridHeight()/2, this.getExits().get(this.getDirections().get(s)));

                        placeOnGrid(getGridWidth()-1, (getGridHeight()/2)+1, new RecervedGridPlace());
                        placeOnGrid(getGridWidth()-1, (getGridHeight()/2)-1, new RecervedGridPlace());
                        placeOnGrid(getGridWidth()-2, getGridHeight()/2, new RecervedGridPlace());

                    } catch (GridPlaceFull e){
                        System.out.println("Der skete en fejl under generationen af kortet");
                    }
                    break;
                case "West" :
                    try {
                        placeOnGrid(getGridWidth()/2, getGridHeight()-1,this.getExits().get(this.getDirections().get(s)));

                        placeOnGrid((getGridWidth()/2)+1, getGridHeight()-1,new RecervedGridPlace());
                        placeOnGrid((getGridWidth()/2)-1, getGridHeight()-1,new RecervedGridPlace());
                        placeOnGrid(getGridWidth()/2, getGridHeight()-2,new RecervedGridPlace());

                    } catch (GridPlaceFull e){
                        System.out.println("Der skete en fejl under generationen af kortet");
                    }
                    break;
                case "East" :
                    try {
                        placeOnGrid(getGridWidth()/2, 0,this.getExits().get(this.getDirections().get(s)));

                        placeOnGrid((getGridWidth()/2)+1,0,new RecervedGridPlace());
                        placeOnGrid((getGridWidth()/2)-1,0,new RecervedGridPlace());
                        placeOnGrid(getGridWidth()/2,1,new RecervedGridPlace());

                    } catch (GridPlaceFull e){
                        System.out.println("Der skete en fejl under generationen af kortet");
                    }
                    break;
            }
        }

        while (this.plasticAmount > plasticPlaced) {
            Random random = new Random();
            x = random.nextInt(getGridWidth());
            y = random.nextInt(getGridHeight());
            plasticTypeSelected = random.nextInt(plasticTypes.length);

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
