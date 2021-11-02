package Exceptions;

public class OutOfEnergyException extends EnergyOutOfBoundsException {
    public OutOfEnergyException() {
        super("Out of energy");
    }
}
