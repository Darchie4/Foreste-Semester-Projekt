package Exceptions;

public class FullInventoryException extends IlleagleItemMovementException{
    public FullInventoryException() {
        super("The inventory is full");
    }
}
