package worldofzuul;

import java.awt.*;
import java.util.ArrayList;

public class FacilityRoom extends Room {
    private ArrayList<Facility> facilities = new ArrayList<Facility>();

    FacilityRoom(String description){
        super(description);
    }

    public void setFacility(Facility facility){
        this.facilities.add(facility);
    }

    public ArrayList<Facility> getFacilities() {
        return this.facilities;
    }
}
