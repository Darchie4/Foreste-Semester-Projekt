package worldofzuul;

import Exceptions.EmptyHandException;
import Exceptions.FullHandException;
import Exceptions.FullInventoryException;
import Exceptions.OutOfPointsException;
import Rooms.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    public static Player player;


    public Game(Player player) {
        createRooms();
        this.parser = new Parser();
        Game.player = player;
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
        Facility hardContainer = new Facility("h\u00E5rd plastikcontainer",
                "Her i putter du h\u00E5rd plastik", PlasticType.HARD, 2 );
        facility.setFacility(hardContainer, "h\u00E5rd plastikcontainer" );

        Facility softContainer = new Facility("bl\u00F8d plastikcontainer",
                "Her i putter du bl\u00F8d plastik", PlasticType.SOFT, 1 );
        facility.setFacility(softContainer, "bl\u00F8d plastikcontainer");

        Facility pantMachine = new Facility("pantautomat",
                "Her i putter du flasker og d\u00E5ser med pant", PlasticType.PANT, 3 );
        facility.setFacility(pantMachine, "pantautomat");
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
        System.out.println("Hej dette er \"plastik fantastik\" spillet" );
        System.out.println("Her skal du kunne");
        System.out.println("Har du brug for hjælp skriv \"" + CommandWord.HELP + "\"");
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
        } else if (commandWord == CommandWord.GO) {
                    goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
                    wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            if (currentRoom instanceof Shop shopping) {
                buyFromShop(shopping, command.getSecondWord());
            }
        } else if (commandWord == CommandWord.COLLECT){
          //  player.addItemToInventory(); //Mads skal implementere dette. Der er behov for et checkup på grid location.
        } else if (commandWord == CommandWord.USE){
            for (Item item: player.getInventory().getItems()) {
                if(item.getName().equals(command.getSecondWord()))
                    try {
                        player.addItemToHand(item);
                    } catch (FullHandException ex) {
                        System.out.println("Du har allerede en ting i hænderne!");
                    }
            }
        } else if (commandWord == CommandWord.REMOVE){
            try {
                player.removeItemFromHand();
            } catch (EmptyHandException ex){
                System.out.println("Du har intet i hænderne!");
            }
        } else if (commandWord == CommandWord.RECYCLE){
            if (currentRoom instanceof FacilityRoom room) {
                String plasticType = command.getSecondWord();
                ArrayList<Plastic> foundPlastic = getPlasticFromInventory();
                Plastic plastic = filterPlastic(plasticType, foundPlastic);
                Facility chosenFacility = selectFacility(room);
                chosenFacility.use(plastic.getType());
            }
        }
        return wantToQuit;
    }

    private void buyFromShop (Shop shop, String command) {
        for (Item item : shop.getAllItems()) {
            if (item.getName().equals(command)) {
                try {
                    shop.buy((Equipment) item);
                } catch (FullInventoryException ex) {
                    System.out.println("Du har desværre ikke plads til genstanden");
                } catch (OutOfPointsException ex) {
                    System.out.println("Du har desværre ikke nok point til genstanden");
                }
            }
        }
    }

    private ArrayList<Plastic> getPlasticFromInventory(){
        ArrayList<Plastic> plasticFound = new ArrayList<Plastic>();
        for (Item item: player.getInventory().getItems()) {
            if (item instanceof Plastic plastic)
                plasticFound.add(plastic);
        }
        return plasticFound;
    }
    private Plastic filterPlastic(String plasticType, ArrayList<Plastic> plasticCollection){
        for (Plastic plastic: plasticCollection) {
            if (plastic.getType().toString().equals(plasticType))
                return plastic;
        }
        System.out.println("Du har intet plastik af den type");
        return null;
    }
    private Facility selectFacility(FacilityRoom room){
        System.out.println("Hvor vil du gerne sortere dit plastik?");
        System.out.println("> h\u00E5rd plastikcontainer \n> bl\u00F8d plastikcontainer \n> pantautomat \n");
        Command command = parser.getCommand();
        String location = command.getCommandWord().toString().toLowerCase();
        Facility chosenFacility = room.getFacilities().get(location);
        return chosenFacility;
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
