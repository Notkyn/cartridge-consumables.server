package ua.notky.cartridge.consumables.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.notky.cartridge.consumables.util.exception.ApplicationException;
import ua.notky.cartridge.consumables.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;

import static ua.notky.cartridge.consumables.util.ExceptionUtil.*;

/*
* Класс для глобальной обработки исключений
* */
@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest req, NoHandlerFoundException e) {
        return getExceptionView(req, e, false, ErrorType.WRONG_REQUEST, null);
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) {
        return getExceptionView(req, appEx, true, appEx.getType(), appEx.getExceptionMsg());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        return getExceptionView(req, e, true, ErrorType.APP_ERROR, null);
    }

    /*
    * Класс для создание страници ошибки:
    * - логирование исключения
    * - отправка деталей исключения на страницу
    * */
    private ModelAndView getExceptionView(HttpServletRequest req,
                                          Exception e,
                                          boolean logException,
                                          ErrorType errorType,
                                          String msg){
        Throwable rootCause = getRootCause(e);
        logException(log, req, rootCause, logException, errorType);

        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("typeMessage", errorType.getErrorMsg());
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? msg : getMessage(rootCause));
        return mav;
    }
}
