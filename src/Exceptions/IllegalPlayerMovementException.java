package Exceptions;

public class IllegalPlayerMovementException extends Exception{
    public IllegalPlayerMovementException() {
        super("The place the player treied to move to was not possible");
    }
    public IllegalPlayerMovementException(String message) {
        super(message);
    }
}
