package Exceptions;

public class FullInventoryException extends Exception {
    public FullInventoryException() {
        super("The inventory is full");
    }
}
