package ua.notky.cartridge.consumables.util.exception;

public class HasDependencyException extends RuntimeException {
    private static final long serialVersionUID = 8344146213259484657L;

    public HasDependencyException(String message) {
        super(message);
    }
}
