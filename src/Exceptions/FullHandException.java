package Exceptions;

public class FullHandException extends Exception {
    public FullHandException(){
        super("Du har allerede en ting i hænderne!");
    }
}
