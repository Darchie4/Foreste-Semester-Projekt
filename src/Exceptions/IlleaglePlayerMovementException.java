package Exceptions;

public class IlleaglePlayerMovementException extends Exception{
    public IlleaglePlayerMovementException() {
        super("The place the player treied to move to was not possible");
    }
    public IlleaglePlayerMovementException(String message) {
        super(message);
    }
}
