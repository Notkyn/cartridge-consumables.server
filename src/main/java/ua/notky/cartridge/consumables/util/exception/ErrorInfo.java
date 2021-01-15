package ua.notky.cartridge.consumables.util.exception;

import java.util.Map;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeMessage;
    private final Map details;
//    private final String[] details;

    public ErrorInfo(String url, ErrorType type, String typeMessage, Map details) {
        this.url = url;
        this.type = type;
        this.typeMessage = typeMessage;
        this.details = details;
    }


//    public ErrorInfo(HashMap<String, String> det, String url, ErrorType type, String typeMessage, String[] details) {
//        this.det = det;
//        this.url = url;
//        this.type = type;
//        this.typeMessage = typeMessage;
//        this.details = details;
//    }

    //    public ErrorInfo(CharSequence url, ErrorType type, String typeMessage, String... details) {
//        this.url = url.toString();
//        this.type = type;
//        this.typeMessage = typeMessage;
//        this.details = details;
//    }
}
