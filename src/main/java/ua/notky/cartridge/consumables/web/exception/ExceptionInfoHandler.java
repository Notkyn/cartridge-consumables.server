package ua.notky.cartridge.consumables.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.notky.cartridge.consumables.util.MessageI18nUtil;
import ua.notky.cartridge.consumables.util.constant.Const;
import ua.notky.cartridge.consumables.util.exception.ErrorInfo;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.stream.Collectors;

import static ua.notky.cartridge.consumables.util.ExceptionUtil.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(value = Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private MessageI18nUtil messageI18nUtil;

    @Autowired
    public void setMessageI18nUtil(MessageI18nUtil messageI18nUtil) {
        this.messageI18nUtil = messageI18nUtil;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo bindValidationError(HttpServletRequest request, Exception exception){
        logException(log, request, exception, true, VALIDATION_ERROR);

        BindingResult result = ((MethodArgumentNotValidException) exception).getBindingResult();

        Map details = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        v -> messageI18nUtil.getMessageBindFormat(v.getCode())));

        return getErrorInfo(request,
                exception,
                VALIDATION_ERROR,
                messageI18nUtil.getMessageErrorType(VALIDATION_ERROR),
                details);
    }

    @ResponseStatus(HttpStatus.CONFLICT)  //409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo duplicateNameError(HttpServletRequest request, DataIntegrityViolationException exception){

        if(isDuplicateEntryException(exception)){
            logException(log, request, exception, true, VALIDATION_ERROR);

            return getErrorInfo(request,
                    exception,
                    VALIDATION_ERROR,
                    messageI18nUtil.getMessageErrorType(VALIDATION_ERROR),
                    Map.of(Const.NAME_FIELD, messageI18nUtil.getMessage(Const.DUPLICATE_EXCEPTION_CODE)));
        }

        logException(log, request, exception, true, DATA_ERROR);
        return getErrorInfo(request,
                exception,
                DATA_ERROR,
                messageI18nUtil.getMessageErrorType(DATA_ERROR));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  //422
    @ExceptionHandler(NotFoundDataException.class)
    public ErrorInfo dataNotFoundError(HttpServletRequest request, Exception exception){
        logException(log, request, exception, true, DATA_NOT_FOUND);
        return getErrorInfo(request,
                exception,
                DATA_NOT_FOUND,
                messageI18nUtil.getMessageErrorType(DATA_NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  //422
    @ExceptionHandler(HasDependencyException.class)
    public ErrorInfo hasDependencyError(HttpServletRequest request, Exception exception){
        logException(log, request, exception, true, HAS_DEPENDENCY);
        return getErrorInfo(request,
                exception,
                HAS_DEPENDENCY,
                messageI18nUtil.getMessageErrorType(HAS_DEPENDENCY));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest request, Exception exception) {
        logException(log, request, exception, true, APP_ERROR);
        return getErrorInfo(request,
                exception,
                APP_ERROR,
                messageI18nUtil.getMessageErrorType(APP_ERROR));
    }
}
