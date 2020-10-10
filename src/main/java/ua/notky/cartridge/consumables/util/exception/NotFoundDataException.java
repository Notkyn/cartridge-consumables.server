package ua.notky.cartridge.consumables.util.exception;

public class NotFoundDataException extends RuntimeException {
    private static final long serialVersionUID = 3397051392875875657L;

    public NotFoundDataException(String message) {
        super(message);
    }
}
