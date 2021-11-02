package Exceptions;

public class IlleagleItemMovementException extends Exception{
    public IlleagleItemMovementException() {
        super("Tried to move Item to Illeagle location");
    }
    public IlleagleItemMovementException(String message) {
        super(message);
    }
}
