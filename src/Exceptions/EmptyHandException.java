package Exceptions;

public class EmptyHandException extends Exception {
    public EmptyHandException() {
        super("Du har intet i hænderne!");
    }
}
