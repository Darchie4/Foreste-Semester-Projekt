package Exceptions;

public class FullHandException extends IlleagleItemMovementException{
    public FullHandException(){
        super("Can't put item in full hand");
    }
}
