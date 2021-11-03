package worldofzuul;

import Exceptions.FullInventoryException;
import Rooms.*;

import java.util.ArrayList;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    public static Player player;


    public Game(Player player)
    {
        createRooms();
        this.parser = new Parser();
        this.player = player;
    }


    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        FacilityRoom facility;
        facility = new FacilityRoom("Du er nu på genbrugsstationen");
        createFacilities(facility);
        Shop shop;


        Biome forest, playground, park, beach;
        playground = new Biome("du er tager over til legepladsen", 10);
        forest = new Biome("du er på tur i parken", 15);
        park = new Biome("du er på tur i skoven", 20);
        beach = new Biome("du er taget til stranden", 25);


        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        currentRoom = outside;
    }

    public void createFacilities(FacilityRoom facility){
        Facility hardContainer = new Facility("hård plastik container",
                "Her i putter du hård plastik", PlasticType.HARD, 2 );
        facility.setFacility(hardContainer);

        Facility softContainer = new Facility("blød plastik container",
                "Her i putter du blød plastik", PlasticType.SOFT, 1 );
        facility.setFacility(softContainer);
        //Brug unicode karakter istedet for æøå
        Facility pantMachine = new Facility("pantautomat",
                "Her i putter du flasker og dåser med pant", PlasticType.PANT, 3 );
        facility.setFacility(pantMachine);
    }
     public void createShop(Shop shop) throws FullInventoryException {
        Item Trashgrabber = new Equipment(5, 15, "Denne gribetang hjælper dig med at samle plastik op"); //add parameters
        ArrayList<Item> temp = new ArrayList<Item>();
        temp.add(Trashgrabber);
        shop = new Shop(temp, "Her kan du købe ting, som hjælper dig med at samle plastik op med");
    }



    public void play(){

        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
