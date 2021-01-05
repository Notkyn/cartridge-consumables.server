package ua.notky.cartridge.consumables.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final String exception;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage, String exception, String... details) {
        this.url = url.toString();
        this.type = type;
        this.typeMessage = typeMessage;
        this.exception = exception;
        this.details = details;
    }
}
