package Rooms;

import worldofzuul.Game;

public class Home extends Room{

    public Home(String description){
        super(description);
    }

    public void sleep (){
        Game.player.setEnergyToMax();
    }
}




