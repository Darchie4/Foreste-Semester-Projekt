package Rooms;

import Exceptions.GridPlaceFull;
import Exceptions.IllegalPlayerMovementException;
import worldofzuul.Game;
import worldofzuul.Placeble;
import worldofzuul.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;


public class Room implements Placeble{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, String> directions;
    private Placeble[][] grid;
    private int[] playerLocation; // playerLocation[0] = x coordinat, playerLocation[1] = y coordinat

    public Room(String description, int gridWith, int gridHeight) {
        playerLocation = new int[]{0,0};
        this.description = description;
        exits = new HashMap<String, Room>();
        directions = new HashMap<String, String>();
        this.grid = new Placeble[gridWith][gridHeight];
    }

    public Room(String description) {
        this(description,10,10);
    }

    public void movePlayer(char direction, int length, Game game) throws IllegalPlayerMovementException {
        direction = Character.toLowerCase(direction);
        switch (direction){
            case ('s'):
                if (playerLocation[0]+length > getGridHeight()){
                    System.out.println(Character.toString(direction) + (playerLocation[0]+length) + "Maks er: " + getGridHeight());

                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0]; x < playerLocation[0]+length; x++) {
                    if (grid[x][playerLocation[0]] instanceof Room){
                        game.setCurrentRoom((Room) grid[x][playerLocation[1]]);
                        game.getCurrentRoom().getPlayerLocation()[0] = 0;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()/2;
                        return;
                    }
                    if (grid[x][playerLocation[0]] != null && !(grid[x][playerLocation[0]] instanceof Player)) {
                        System.out.println("Der var noget i vejen " + Character.toString(direction));
                        throw new IllegalPlayerMovementException();
                    }
                }
                playerLocation[0] += length;
                System.out.println(Arrays.toString(playerLocation));
                System.out.println("Alt burde have virket " + Character.toString(direction));
                return;
            case ('n'):
                if (playerLocation[0]-length < 0){
                    System.out.println(Character.toString(direction) + (playerLocation[0]-length));
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0]-length; x < playerLocation[0]; x++) {
                    if (grid[x][playerLocation[0]] instanceof Room){
                        game.setCurrentRoom((Room) grid[x][playerLocation[1]]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()-1;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()/2;
                        return;
                    }
                    if (grid[x][playerLocation[0]] != null) {
                        System.out.println("Der var noget i vejen " + Character.toString(direction));
                        throw new IllegalPlayerMovementException();
                    }
                }
                playerLocation[0] -= length;
                System.out.println("Alt burde have virket" + direction);
                return;
            case ('e'):
                if (playerLocation[1]+length > getGridWidth()){
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1]; y < playerLocation[1]+length; y++) {
                    if (grid[playerLocation[1]][y] instanceof Room){
                        game.setCurrentRoom((Room) grid[playerLocation[0]][y]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()/2;
                        game.getCurrentRoom().getPlayerLocation()[1] = 0;
                        return;
                    }
                    if (grid[playerLocation[1]][y] != null) {
                        throw new IllegalPlayerMovementException();
                    }
                }
                playerLocation[1] += length;
                return;
            case ('w'):
                if (playerLocation[1]-length < 0){
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1]-length; y < playerLocation[1]; y++) {

                    if (grid[playerLocation[1]][y] instanceof Room){
                        game.setCurrentRoom((Room) grid[playerLocation[0]][y]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()/2;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()-1;
                        return;
                    }
                    if (grid[playerLocation[1]][y] != null) {
                        throw new IllegalPlayerMovementException();
                    }
                }
                playerLocation[1] -= length;
                return;
        }
        throw new IllegalPlayerMovementException();
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

    public void printGrid(Player player){
        Placeble[][] printGrid = grid.clone();
        printGrid[playerLocation[0]][playerLocation[1]] = player;
        String horisontalLine = "";
        for (int i = 0; i < getGridWidth()*2+1; i++) {
            horisontalLine += "━" ;
        }
        ArrayList<Character> printArray = new ArrayList<>();
        System.out.println("┍" + horisontalLine + "┑");

        for (Placeble[] placebles : printGrid) {
            printArray.add('|');
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
        printGrid[playerLocation[0]][playerLocation[1]] = null;
    }

        /*
        for (Placeble[] placebles : printGrid) {
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

         */

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

<<<<<<< HEAD
    private String getExitString()
    {
        String returnString = "Steder at tage hen:";
=======
    private String getExitString() {
        String returnString = "Exits:";
>>>>>>> 0c40e200b48f17a100dca3460293fed5ec5daa11
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

    public int[] getPlayerLocation() {
        return playerLocation;
    }

    public void placeOnGrid(int x, int y, Placeble item) throws GridPlaceFull{
        if (grid[x][y] != null){
            throw new GridPlaceFull();
        }
        grid[x][y] = item;
    }

}

