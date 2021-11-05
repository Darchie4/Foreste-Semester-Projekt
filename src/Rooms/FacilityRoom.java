package Rooms;

import java.util.ArrayList;
import java.util.HashMap;

public class FacilityRoom extends Room {

    private HashMap<String, Facility> facilities = new HashMap<String, Facility>();
    public FacilityRoom(String description){
        super(description);
    }

    public void setFacility(Facility facility, String name){
        this.facilities.put(name, facility);
    }

    public HashMap<String, Facility>  getFacilities() {
        return this.facilities;
    }
}
