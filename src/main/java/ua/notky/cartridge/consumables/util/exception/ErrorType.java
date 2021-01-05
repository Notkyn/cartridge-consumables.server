package ua.notky.cartridge.consumables.util.exception;

public enum ErrorType {
    APP_ERROR("Application Error"),
    DATA_NOT_FOUND("Data Not Found"),
    WRONG_REQUEST("Wrong Request");

    private final String errorMsg;

    ErrorType(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
