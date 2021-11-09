package worldofzuul;

import Exceptions.IllegalPlayerMovementException;
import Rooms.Biome;
import Rooms.Room;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {

        Plastic plastic = Plastic.plasticFactory(PlasticType.HARD);
        plastic.print();
        System.out.println();System.out.println();System.out.println();
        Plastic plastic1 = Plastic.plasticFactory(PlasticType.SOFT);
        plastic1.print();
        System.out.println();System.out.println();System.out.println();
        Plastic plastic2 = Plastic.plasticFactory(PlasticType.PANT);
        plastic2.print();

        /*Player player = new Player();
        Game game = new Game(player);
        Biome room = new Biome("This is a test room", 10, 8, 8);
        Biome beach = new Biome("This is a test room", 10, 8, 8);
        Room home = new Room("This is a test room", 4, 4);
        room.setExit("North","Stranden", beach);
        room.setExit("South","Hjem", home);
        room.generateGrid();
        room.printGrid(player);
        try{
            room.movePlayer('s', 2, game);
        } catch (IllegalPlayerMovementException e){
            System.out.println("Du er dum2\n" + e);
        }
        room.printGrid(player);
        try{
            room.movePlayer('e', 1, game);
        } catch (IllegalPlayerMovementException e){
            System.out.println("Du er dum2\n" + e);
        }
        try{
            room.movePlayer('w', 1, game);
        } catch (IllegalPlayerMovementException e){
            System.out.println("Du er dum3\n" + e);
        }
        System.out.println(Arrays.toString(game.getCurrentRoom().getPlayerLocation()));
        room.printGrid(player); */

    }
}
