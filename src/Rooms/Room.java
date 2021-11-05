package Rooms;

import Exceptions.GridPlaceFull;
import worldofzuul.Placeble;

import java.util.Set;
import java.util.HashMap;


public class Room implements Placeble{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, String> directions;
    private Placeble[][] grid;


    public Room(String description, int gridWith, int gridHeight) {
        this.description = description;
        exits = new HashMap<String, Room>();
        directions = new HashMap<String, String>();
        this.grid = new Placeble[gridWith][gridHeight];
    }

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.grid = new Placeble[10][10];
    }

    public void generateGrid(){
        String exitsString[] = this.directions.keySet().toArray(new String[0]);
        for (String s : exitsString) {
            switch (s) {
                case "North" :
                    grid[0][getGridHeight()/2] = this.exits.get(s);
                    break;
                case "South" :
                    grid[getGridWidth()-1][getGridHeight()/2] = this.exits.get(s);
                    break;
                case "West" :
                    grid[getGridWidth()/2][getGridHeight()-1] = this.exits.get(s);
                    break;
                case "East" :
                    grid[getGridWidth()/2][0] = this.exits.get(s);
                    break;

            }
        }
    }

    public void printGrid(){
        String horisontalLine = "";
        for (int i = 0; i < getGridWidth()*2+1; i++) {
            horisontalLine += "━" ;
        }

        System.out.println("┍" + horisontalLine + "┑");
        for (Placeble[] placebles : grid) {
            System.out.print("|");
            for (Placeble placeble : placebles) {
                if (placeble == null) {
                    System.out.print(" /");
                }else {
                    System.out.print(" " + placeble.getSymbol());
                }
            }
            System.out.println(" |");
        }
        System.out.println("┕" + horisontalLine + "┙");
    }

    public void setExit(String direction, String place, Room neighbor) {
        directions.put(direction,place);
        exits.put(place, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Placeble[][] getGrid() {
        return grid;
    }

    public int getGridWidth(){
        return this.grid.length;
    }

    public int getGridHeight(){
        return this.grid[1].length;
    }

    @Override
    public char getSymbol() {
        return '#';
    }

    public HashMap<String, String> getDirections() {
        return directions;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public void placeOnGrid(int x, int y, Placeble item) throws GridPlaceFull{
        if (grid[x][y] != null){
            throw new GridPlaceFull();
        }
        grid[x][y] = item;
    }

}

