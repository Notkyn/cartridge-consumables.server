package ua.notky.cartridge.consumables.util.exception;

public class IllegalRequestDataException extends RuntimeException {
    private static final long serialVersionUID = 5755589723921503408L;

    public IllegalRequestDataException(String message) {
        super(message);
    }
}
