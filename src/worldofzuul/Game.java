package worldofzuul;

import Exceptions.EmptyHandException;
import Exceptions.FullHandException;
import Exceptions.FullInventoryException;
import Exceptions.OutOfPointsException;
import Rooms.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    public static Player player;


    public Game(Player player) {
        createRooms();
        this.parser = new Parser();
        Game.player = player;
    }

    private void createRooms() {
        Room home, map;
        map = new Room("du kigger på kortet");
        home = new Room("Du er nu derhjemme");
        home.setExit("South-West","kort", map);

        // facility creation
        FacilityRoom facility = new FacilityRoom("Du er nu på genbrugsstationen");
        createFacilities(facility);
        facility.setExit("East","hjem", home);

        // shop creation
        Shop shop = createShop();
        shop.setExit("North","Hjem", home);

        // biome creation
        Biome forest, playground, park, beach;

        playground = new Biome("du er tager over til legepladsen", 10, 6,6);
        park = new Biome("du er på tur i parken", 20,10,10);
        playground.setExit("South","Hjem", home);
        playground.setExit("North","Parken", park);

        park.setExit("South","Legepladsen", playground);
        forest = new Biome("du er p\u00E5 tur i skoven", 15, 8,8);
        forest.setExit("west","hjem", home);

        park = new Biome("du er p\u00E5 tur i parken", 20, 10, 10);
        park.setExit("south", "legeplads", home);

        forest = new Biome("du er på tur i skoven", 15,8,8);
        beach = new Biome("du er taget til stranden", 25,12,12);
        forest.setExit("West","Hjem", home);
        forest.setExit("East","Starnd", beach);
        beach.setExit("West","hjem", home);


        map.setExit("North","Legepladsen", playground);
        map.setExit("South","Parken", park);
        map.setExit("East","Skoven", forest);
        map.setExit("West","Stranden",beach);
        map.setExit("North-East","Genbrugsstationen",facility);
        map.setExit("North-west","Butikken", shop);
        map.setExit("North-south","Hjem", home);

        home.generateGrid();
        shop.generateGrid();
        facility.generateGrid();
        forest.generateGrid();
        beach.generateGrid();
        playground.generateGrid();
        park.generateGrid();



        currentRoom = home;
    }

    public void createFacilities(FacilityRoom facility){
        Facility hardContainer = new Facility("h\u00E5rd plastikcontainer",
                "Her i putter du h\u00E5rd plastik", PlasticType.HARD, 2 );
        facility.setFacility(hardContainer, "h\u00E5rd" );

        Facility softContainer = new Facility("bl\u00F8d plastikcontainer",
                "Her i putter du bl\u00F8d plastik", PlasticType.SOFT, 1 );
        facility.setFacility(softContainer, "bl\u00F8d");

        Facility pantMachine = new Facility("pantautomat",
                "Her i putter du flasker og d\u00E5ser med pant", PlasticType.PANT, 3 );
        facility.setFacility(pantMachine, "pantautomat");
    }

     public Shop createShop()  {
        try {
            Item Trashgrabber = new Equipment(5, 15, "Denne gribetang hj\u00E6lper dig med at samle plastik op", "Gribetang",1); //add parameters
            ArrayList<Item> temp = new ArrayList<Item>();
            temp.add(Trashgrabber);
            return new Shop(temp, "Her kan du k\u00F8be ting, som hj\u00E6lper dig med at samle plastik op med");
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
        System.out.println("Tak for at spille \"Plastik Fantastik\" spillet. Farvel.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Hej dette er \"plastik fantastik\" spillet" );
        System.out.println("Her skal du kunne"); //MANGLER BESKRIVELSE****************
        System.out.println("Har du brug for hj\u00E6lp skriv \"" + CommandWord.HELP + "\"");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command){
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
            Item item = player.getInventory().searchItemName(command.getSecondWord());
                if (player.getEquipmentInHand() == null) {
                    try {
                        player.addEquipmentToHand(equipment);
                    } catch (FullHandException e) {
                        e.printStackTrace();
                    }
                }
            }
        else if (commandWord == CommandWord.REMOVE){
            try {
                player.removeEquipmentFromHand();
            } catch (EmptyHandException ex){
                System.out.println();
            }
        } else if (commandWord == CommandWord.RECYCLE){
            if (currentRoom instanceof FacilityRoom room) {
                Plastic foundPlastic;
                Facility chosenFacility;
                String plasticType = command.getSecondWord();

                foundPlastic = getPlasticFromInventory(plasticType);
                chosenFacility = selectFacility(room);
                player.removeItemFromInventory(foundPlastic);
                chosenFacility.use(foundPlastic.getPlasticType());
            } else {
                System.out.println("Hov!, du er ikke på genbrugsstationen");
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
                    System.out.println("Du har desv\u00E6rre ikke plads til genstanden");
                } catch (OutOfPointsException ex) {
                    System.out.println("Du har desv\u00E6rre ikke nok point til at k\u00F8be genstanden");
                }
            }
        }
    }

    private Plastic getPlasticFromInventory(String chosenType){
        for (Item item: player.getInventory().getItems()) {
            if (item instanceof Plastic plastic && plastic.getPlasticType().getName().equals(chosenType))
                return plastic;
        }
        System.out.println("Du har desværre intet " + chosenType + " på dig");
        return null;
    }

    private Facility selectFacility(FacilityRoom room){
        System.out.println("Hvor vil du gerne sortere dit plastik?");
        System.out.println("> h\u00E5rd plastikcontainer \n> bl\u00F8d plastikcontainer \n> pantautomat \n");
        Scanner chosenContainer = new Scanner(System.in);
        String location = chosenContainer.next();
        return room.getFacilities().get(location);
    }


    private void printHelp() {
        System.out.println("Du har spurgt om hj\u00E6lp");
        System.out.println(currentRoom.getShortDescription() + "\n");
        System.out.println("Dette kan du g\u00F8re:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Hvor vil du hen igen?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Hov! Der kan du ikke g\u00E5 hen :(");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("g\u00E5 ud af hvad?");
            return false;
        }
        else {
            return true;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
