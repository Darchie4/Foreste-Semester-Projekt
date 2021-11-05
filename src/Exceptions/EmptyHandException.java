package Exceptions;

public class EmptyHandException extends IlleagleItemMovementException{
    public EmptyHandException() {
        super("Du har intet i hænderne!"));
    }
}
