package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import ua.notky.cartridge.consumables.util.constant.Const;
import ua.notky.cartridge.consumables.util.exception.ErrorInfo;
import ua.notky.cartridge.consumables.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ExceptionUtil {

    private static String getExceptionMessage(Throwable e) {
        log.info("Get Message for Exception [exception={}]", e);

        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    private static Throwable getRootCause(Throwable t) {
        log.info("Get Root Cause for Exception [exception={}]", t.toString());

        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    /*
    * Логирование исключения
    * */
    public static void logException(Logger log,
                                    HttpServletRequest request,
                                    Exception exception,
                                    boolean logException,
                                    ErrorType errorType){


        Throwable throwable = getRootCause(exception);

        if (logException) {
            log.error(errorType + " at request " + request.getRequestURL(), throwable);
        } else {
            log.warn("{} at request  {}: {}", errorType, request.getRequestURL(), throwable.toString());
        }
    }

    /*
     * Создание страници ошибки:
     * - отправка деталей исключения на страницу
     * */
    public static ModelAndView getExceptionView(Exception exception,
                                          ErrorType errorType,
                                          String msg){
        log.info("Create ModelAndView for Request: [exception={}]", exception.toString());

        Throwable rootCause = getRootCause(exception);

        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("typeMessage", errorType.getErrorCode());
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? msg : getExceptionMessage(rootCause));
        return mav;
    }

    /*
    * Обьект для детального описания исключения
    * */
    public static ErrorInfo getErrorInfo(HttpServletRequest request,
                                  Exception exception,
                                  ErrorType errorType,
                                  String errorMsg,
                                  String... details) {
        log.info("Create ErrorInfo for Request: [requestUrl={}], [exception={}]",
                request.getRequestURL(), exception.toString());

        Throwable rootCause = getRootCause(exception);

        return new ErrorInfo(request.getRequestURL(),
                errorType,
                errorMsg,
                details.length != 0 ? details : new String[]{getExceptionMessage(rootCause)});
    }

    public static boolean isDuplicateEntryException(Exception ex){
        log.info("Check Exception for Duplicate Items[exception={}]", ex.toString());

        String rootMsg = getRootCause(ex).getMessage();

        return rootMsg != null && rootMsg.toLowerCase().contains(Const.DUPLICATE_EXCEPTION);
    }
}
