package Rooms;

import Exceptions.GridPlaceFull;
import Exceptions.IlleaglePlayerMovementException;
import worldofzuul.Game;
import worldofzuul.Placeble;
import worldofzuul.Player;

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

    public void movePlayer(char direction, int length, Game game) throws IlleaglePlayerMovementException {
        direction = Character.toLowerCase(direction);
        switch (direction){
            case ('n'):
                if (playerLocation[0]+direction > getGridHeight()){
                    throw new IlleaglePlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0]; x < playerLocation[0]+direction; x++) {
                    if (grid[x][playerLocation[0]] instanceof Room){
                        game.setCurrentRoom((Room) grid[x][playerLocation[1]]);
                        game.getCurrentRoom().getPlayerLocation()[0] = 0;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()/2;
                        return;
                    }
                    if (grid[x][playerLocation[0]] != null) {
                        throw new IlleaglePlayerMovementException();
                    }
                }
                playerLocation[0] = playerLocation[0]+direction;
                break;
            case ('s'):
                if (playerLocation[0]-direction < 0){
                    throw new IlleaglePlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0]-direction; x < playerLocation[0]; x++) {
                    if (grid[x][playerLocation[0]] instanceof Room){
                        game.setCurrentRoom((Room) grid[x][playerLocation[1]]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()-1;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()/2;
                        return;
                    }
                    if (grid[x][playerLocation[0]] != null) {
                        throw new IlleaglePlayerMovementException();
                    }
                }
                playerLocation[0] = playerLocation[0]-direction;
                break;
            case ('e'):
                if (playerLocation[1]+direction > getGridWidth()){
                    throw new IlleaglePlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1]; y < playerLocation[1]+direction; y++) {
                    if (grid[playerLocation[1]][y] instanceof Room){
                        game.setCurrentRoom((Room) grid[playerLocation[0]][y]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()/2;
                        game.getCurrentRoom().getPlayerLocation()[1] = 0;
                        return;
                    }
                    if (grid[playerLocation[1]][y] != null) {
                        throw new IlleaglePlayerMovementException();
                    }
                }
                playerLocation[1] = playerLocation[1]+direction;
                break;
            case ('w'):
                if (playerLocation[1]-direction < 0){
                    throw new IlleaglePlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1]-direction; y < playerLocation[1]; y++) {

                    if (grid[playerLocation[1]][y] instanceof Room){
                        game.setCurrentRoom((Room) grid[playerLocation[0]][y]);
                        game.getCurrentRoom().getPlayerLocation()[0] = game.getCurrentRoom().getGridHeight()/2;
                        game.getCurrentRoom().getPlayerLocation()[1] = game.getCurrentRoom().getGridWidth()-1;
                        return;
                    }
                    if (grid[playerLocation[1]][y] != null) {
                        throw new IlleaglePlayerMovementException();
                    }
                }
                playerLocation[1] = playerLocation[1]-direction;
                break;
        }
        throw new IlleaglePlayerMovementException();
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
        printGrid[playerLocation[0]][playerLocation[1]] = (Placeble) player;
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

