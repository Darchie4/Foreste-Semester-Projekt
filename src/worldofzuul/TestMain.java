package worldofzuul;

import Exceptions.IlleaglePlayerMovementException;
import Rooms.Biome;
import Rooms.Room;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        Player player = new Player();
        Game game = new Game(player);
        Biome room = new Biome("This is a test room", 10, 8, 8);
        Biome beach = new Biome("This is a test room", 10, 8, 8);
        Room home = new Room("This is a test room", 4, 4);
        room.setExit("North","Stranden", beach);
        room.setExit("South","Hjem", home);
        room.generateGrid();
        room.printGrid(player);
        try{
            room.movePlayer('n', 1, game);
        } catch (IlleaglePlayerMovementException e){
            System.out.println("Du er dum\n" + e);
        }
        try{
            room.movePlayer('s', 3, game);
        } catch (IlleaglePlayerMovementException e){
            System.out.println("Du er dum2\n" + e);
        }
        System.out.println(Arrays.toString(game.getCurrentRoom().getPlayerLocation()));
        room.printGrid(player);

    }
}
