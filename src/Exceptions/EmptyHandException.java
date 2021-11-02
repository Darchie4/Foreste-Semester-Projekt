package Exceptions;

public class EmptyHandException extends IlleagleItemMovementException{
    public EmptyHandException() {
        super("Hand is empty!");
    }
}
