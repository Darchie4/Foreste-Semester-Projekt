package Rooms;

import worldofzuul.Game;

public class Home extends Room{

    Home(String description){
        super(description);
    }

    void sleep (){
        Game.player.setEnergyToMax();
    }

}
