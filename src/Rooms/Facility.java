package Rooms;

import worldofzuul.PlasticType;

/**
 * Facilities "use method" takes in a plastictype as an int and compares it to the accepted type.
 * here point is given and void is returned.
 * Note: inventory.remove.item(Item item) needs to check instance of Item before using the facilities.use method, so equipment can't be lost during sorting.
 *
 * Accepted plastic types are ints and is noted as: 1 = bloedplast, 2 = haardplast, 3 = pant
 * */


public class Facility {
    private String name;
    private String description;
    private PlasticType acceptedPlasticType;
    private int reward;

    public Facility(String name, String description, PlasticType acceptedPlasticType, int reward){
        this.description = description;
        this.name = name;
        this.acceptedPlasticType = acceptedPlasticType;
        this.reward = reward;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlasticType getAcceptedPlasticType() {
        return acceptedPlasticType;
    }

    public void setAcceptedPlasticType(PlasticType acceptedPlasticType) {
        this.acceptedPlasticType = acceptedPlasticType;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getName(){
        return this.name;
    }

    public void use(PlasticType plasticType){
        if (plasticType == acceptedPlasticType) {
            player.addPoints(this.reward);
            System.out.println("Hurrah! det var den rigtige slags plastik. Du får " + this.reward + " point :)");
        } else
            System.out.println("ØV! Det er desværre den forkerte slags plastik. Ingen point :(");
    }
}

