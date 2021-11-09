package Rooms;

import Exceptions.GridPlaceFull;
import Exceptions.IllegalPlayerMovementException;
import worldofzuul.Game;
import worldofzuul.Drawable;
import worldofzuul.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;


public class Room implements Drawable {
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, String> directions;
    private Drawable[][] grid;
    private int[] playerLocation; // playerLocation[0] = x coordinat, playerLocation[1] = y coordinat

    public Room(String description, int gridWith, int gridHeight) {
        playerLocation = new int[]{0, 0};
        this.description = description;
        exits = new HashMap<String, Room>();
        directions = new HashMap<String, String>();
        this.grid = new Drawable[gridWith][gridHeight];
    }

    public Room(String description) {
        this(description, 10, 10);
    }

    public void movePlayer(char direction, int length, Game game) throws IllegalPlayerMovementException {
        direction = Character.toLowerCase(direction);
        switch (direction) {
            case ('s'):
                if (playerLocation[0] + length > getGridHeight()) {
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0]; x < playerLocation[0] + length; x++) {
                    if (isRoom(grid[x][playerLocation[0]])) {
                        movePlayerToNewRoom(game, grid[x][playerLocation[1]], 0, game.getCurrentRoom().getGridWidth() / 2);
                        return;
                    }
                    testIfThingsIsInTheWayX(x);
                }
                playerLocation[0] += length;
                return;
            case ('n'):
                if (playerLocation[0] - length < 0) {
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int x = this.playerLocation[0] - length; x < playerLocation[0]; x++) {
                    if (isRoom(grid[x][playerLocation[0]])) {
                        movePlayerToNewRoom(game, grid[x][playerLocation[1]], getCurrentRoomHeight(game) - 1, getCurrentRoomWidth(game) / 2);
                        return;
                    }
                    testIfThingsIsInTheWayX(x);
                }
                playerLocation[0] -= length;
                return;
            case ('e'):
                if (playerLocation[1] + length > getGridWidth()) {
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1]; y < playerLocation[1] + length; y++) {
                    if (isRoom(grid[playerLocation[1]][y])) {
                        movePlayerToNewRoom(game, grid[playerLocation[0]][y], getCurrentRoomHeight(game) / 2, 0);
                        return;
                    }
                    testIfThingsIsInTheWayY(y);
                }
                playerLocation[1] += length;
                return;
            case ('w'):
                if (playerLocation[1] - length < 0) {
                    throw new IllegalPlayerMovementException("Du kan ikke gå uden for kortet");
                }
                for (int y = this.playerLocation[1] - length; y < playerLocation[1]; y++) {
                    if (isRoom(grid[playerLocation[1]][y])) {
                        movePlayerToNewRoom(game, grid[playerLocation[0]][y], getCurrentRoomHeight(game)/ 2, getCurrentRoomWidth(game)- 1);
                        return;
                    }
                    testIfThingsIsInTheWayY(y);
                }
                playerLocation[1] -= length;
                return;
        }
        throw new IllegalPlayerMovementException();
    }

    private void testIfThingsIsInTheWayX(int x) throws IllegalPlayerMovementException{
        if (grid[x][playerLocation[1]] != null && !(grid[x][playerLocation[1]] instanceof Player)) {
            throw new IllegalPlayerMovementException();
        }
    }

    private void testIfThingsIsInTheWayY(int y) throws IllegalPlayerMovementException{
        if (grid[playerLocation[0]][y] != null && !(grid[playerLocation[0]][y] instanceof Player)) {
            throw new IllegalPlayerMovementException();
        }
    }

    private boolean isRoom(Drawable object){
        return object instanceof Room;
    }

    private void movePlayerToNewRoom(Game game, Drawable newRoom, int x, int y){
        game.setCurrentRoom((Room) newRoom);
        game.getCurrentRoom().getPlayerLocation()[0] = x;
        game.getCurrentRoom().getPlayerLocation()[1] = y;
    }

    private int getCurrentRoomHeight(Game game){
        return game.getCurrentRoom().getGridHeight();
    }
    private int getCurrentRoomWidth(Game game){
        return game.getCurrentRoom().getGridWidth();
    }

    public void generateGrid() {
        String exitsString[] = this.directions.keySet().toArray(new String[0]);
        for (String s : exitsString) {
            switch (s) {
                case "North":
                    grid[0][getGridHeight() / 2] = this.exits.get(s);
                    break;
                case "South":
                    grid[getGridWidth() - 1][getGridHeight() / 2] = this.exits.get(s);
                    break;
                case "West":
                    grid[getGridWidth() / 2][getGridHeight() - 1] = this.exits.get(s);
                    break;
                case "East":
                    grid[getGridWidth() / 2][0] = this.exits.get(s);
                    break;
            }
        }
    }

    public void printGrid(Player player) {
        Drawable[][] printGrid = grid.clone();
        printGrid[playerLocation[0]][playerLocation[1]] = player;
        String horisontalLine = "";
        for (int i = 0; i < getGridWidth() * 2 + 1; i++) {
            horisontalLine += "━";
        }
        System.out.println("┍" + horisontalLine + "┑");

        for (Drawable[] drawables : printGrid) {
            System.out.print('|');
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    System.out.print(" /");
                } else {
                    System.out.print(" " + drawable.getSymbol());
                }
            }
            System.out.println(" |");
        }
        System.out.println("┕" + horisontalLine + "┙");
        printGrid[playerLocation[0]][playerLocation[1]] = null;
    }

    public void setExit(String direction, String place, Room neighbor) {
        directions.put(direction, place);
        exits.put(place, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return description + ".\n" + getExitString();
    }

        private String getExitString () {
            String returnString = "Exits:";

            Set<String> keys = exits.keySet();
            for (String exit : keys) {
                returnString += " " + exit;
            }
            return returnString;
        }

        public Room getExit (String direction){
            return exits.get(direction);
        }

        public Drawable[][] getGrid () {
            return grid;
        }

        public int getGridWidth() {
            return this.grid.length;
        }

        public int getGridHeight () {
            return this.grid[1].length;
        }

        @Override
        public char getSymbol () {
            return '#';
        }

        public HashMap<String, String> getDirections () {
            return directions;
        }

        public HashMap<String, Room> getExits () {
            return exits;
        }

        public int[] getPlayerLocation () {
            return playerLocation;
        }

        public void placeOnGrid ( int x, int y, Drawable item) throws GridPlaceFull {
            if (grid[x][y] != null) {
                throw new GridPlaceFull();
            }
            grid[x][y] = item;
        }

    }

