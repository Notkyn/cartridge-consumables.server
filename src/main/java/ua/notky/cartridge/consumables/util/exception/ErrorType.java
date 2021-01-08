package ua.notky.cartridge.consumables.util.exception;

public enum ErrorType {
    APP_ERROR("error.type.app_error"),
    DATA_ERROR("error.type.data_error"),
    VALIDATION_ERROR("error.type.validation_error"),
    WRONG_REQUEST("error.type.wrong_request"),
    DATA_NOT_FOUND("error.type.data_not_found"),
    HAS_DEPENDENCY("error.type.has_dependency"),;

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
