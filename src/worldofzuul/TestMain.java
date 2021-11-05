package worldofzuul;

import Rooms.Biome;
import Rooms.Room;

public class TestMain {
    public static void main(String[] args) {
        Biome room = new Biome("This is a test room", 10, 8, 8);
        Biome beach = new Biome("This is a test room", 10, 8, 8);
        Room home = new Room("This is a test room", 4, 4);
        room.setExit("North","Stranden", beach);
        room.setExit("South","Hjem", home);
        room.generateGrid();
        room.printGrid();
    }
}
