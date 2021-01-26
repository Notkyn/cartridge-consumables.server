package ua.notky.cartridge.consumables.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static ua.notky.cartridge.consumables.util.ExceptionUtil.getExceptionView;
import static ua.notky.cartridge.consumables.util.ExceptionUtil.logException;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;

/*
* Класс для глобальной обработки исключений
* */
@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    //todo использование
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest request, NoHandlerFoundException exception) {
        logException(log, request, exception, false, WRONG_REQUEST);
        return getExceptionView(exception, WRONG_REQUEST, null);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {
        logException(log, request, exception, true, APP_ERROR);
        return getExceptionView(exception, APP_ERROR, null);
    }
}
