package worldofzuul;

import Exceptions.FullInventoryException;
import Rooms.*;

import java.util.ArrayList;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    public static Player player;


    public Game(Player player) {
        createRooms();
        this.parser = new Parser();
        this.player = player;
    }

    private void createRooms()
    {
        Room home, map;
        map = new Room("du kigger på kortet");
        home = new Room("Du er nu derhjemme");
        home.setExit("kort", map);


        // facility creation

        FacilityRoom facility = new FacilityRoom("Du er nu på genbrugsstationen");
        createFacilities(facility);
        facility.setExit("hjem", home);

        // shop creation
        Shop shop = createShop();
        shop.setExit("hjem", home);

        // biome creation
        Biome forest, playground, park, beach;
        playground = new Biome("du er tager over til legepladsen", 10);
        playground.setExit("hjem", home);

        forest = new Biome("du er på tur i skoven", 15);
        forest.setExit("hjem", home);

        park = new Biome("du er på tur i parken", 20);
        park.setExit("hjem", home);

        beach = new Biome("du er taget til stranden", 25);
        beach.setExit("hjem", home);

        map.setExit("Legepladsen", playground);
        map.setExit("Parken", park);
        map.setExit("Skoven", forest);
        map.setExit("Stranden",beach);
        map.setExit("Genbrugsstationen",facility);
        map.setExit("Butikken", shop);
        map.setExit("Hjem", home);


        currentRoom = home;
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
     public Shop createShop()  {
        try {
            Item Trashgrabber = new Equipment(5, 15, "Denne gribetang hjælper dig med at samle plastik op", "Gribetang",1); //add parameters
            ArrayList<Item> temp = new ArrayList<Item>();
            temp.add(Trashgrabber);
            return new Shop(temp, "Her kan du købe ting, som hjælper dig med at samle plastik op med");
        } catch (FullInventoryException ex){
            System.out.println("A fatal error has occured when creating shop");
            return null;
        }
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
        System.out.println("Hej dette er \" plastik fantastik spillet" );
        System.out.println("Her skal du kunne");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("Undskyld, det forstod jeg ikke");
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
        System.out.println("Du har spurgt om hjælp");
        System.out.println(currentRoom.getShortDescription() + "\n");
        System.out.println("Dette kan du gøre:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Hvor vil du hen igen?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Hov! Der kan du ikke gå hen :(");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("gå ud af hvad?");
            return false;
        }
        else {
            return true;
        }
    }
}
