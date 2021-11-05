package Rooms;

import Exceptions.GridPlaceFull;
import worldofzuul.Placeble;

import java.util.Set;
import java.util.HashMap;


public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private Placeble[][] grid;

    public Room(String description, int gridWith, int gridHeight) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.grid = new Placeble[gridWith][gridHeight];
    }

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.grid = new Placeble[10][10];
    }

    public void printGrid(){
        for (Placeble[] placebles : grid) {
            for (Placeble placeble : placebles) {
                if (placeble == null) {
                    System.out.print( '/' );
                    continue;
                }
                System.out.print(' ' + placeble.get);
            }
        }
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
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

    public void placeOnGrid(int x, int y, Placeble item) throws GridPlaceFull{
        if (grid[x][y] != null){
            throw new GridPlaceFull();
        }
        grid[x][y] = item;
    }

}

