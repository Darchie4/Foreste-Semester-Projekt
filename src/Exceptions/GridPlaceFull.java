package Exceptions;

public class GridPlaceFull extends IlleagleItemMovementException{
    public GridPlaceFull() {
        super("The grid place is full");
    }
}
