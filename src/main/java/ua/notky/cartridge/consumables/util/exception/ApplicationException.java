package ua.notky.cartridge.consumables.util.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -3079746841557819923L;
    private final ErrorType type;
    private final HttpStatus httpStatus;
    private final String exceptionMsg;
    private final String[] args;

    ApplicationException(ErrorType type, String exceptionMsg, HttpStatus httpStatus, String... args) {
        super(String.format("type=%s, msg=%s, args=%s", type, exceptionMsg, Arrays.toString(args)));
        this.type = type;
        this.exceptionMsg = exceptionMsg;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public ErrorType getType() {
        return type;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public String[] getArgs() {
        return args;
    }
}
