package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import ua.notky.cartridge.consumables.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static String getMessage(Throwable e) {
        log.info("Get Message for Exception [exception={}]", e);

        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable getRootCause(Throwable t) {
        log.info("Get Root Cause for Exception [exception={}]", t.toString());

        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static void logException(Logger log,
                                    HttpServletRequest req,
                                    Throwable throwable,
                                    boolean logException,
                                    ErrorType errorType){
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), throwable);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), throwable.toString());
        }
    }
}
