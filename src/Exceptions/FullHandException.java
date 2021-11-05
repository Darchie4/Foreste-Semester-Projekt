package Exceptions;

public class FullHandException extends IlleagleItemMovementException{
    public FullHandException(){
        super("Du har allerede en ting i hænderne!");
    }
}
