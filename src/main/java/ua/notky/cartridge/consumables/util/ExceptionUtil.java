package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import ua.notky.cartridge.consumables.util.constant.Const;
import ua.notky.cartridge.consumables.util.constant.ConstPath;
import ua.notky.cartridge.consumables.util.exception.ErrorInfo;
import ua.notky.cartridge.consumables.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Pattern;

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
     */
    // todo использование
    public static ModelAndView getExceptionView(Exception exception,
                                          ErrorType errorType,
                                          String msg){
        log.info("Create ModelAndView for Request: [exception={}]", exception.toString());

        Throwable rootCause = getRootCause(exception);

        ModelAndView mav = new ModelAndView(ConstPath.PAGE_EXCEPTION);
        mav.addObject("typeMessage", errorType.getErrorCode());
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? msg : getExceptionMessage(rootCause));
        return mav;
    }

    /*
    * Создание Страници ошибки
    */

    public static ModelAndView getErrorPage(int statusCode, String msg){
        log.info("Create ModelAndView of Error Page for Request: [status={}]", statusCode);

        ModelAndView mav = new ModelAndView(ConstPath.PAGE_ERROR);

        mav.addObject("statusCode", statusCode);
        mav.addObject("message", msg != null ? msg : "");

        return mav;
    }

    /*
    * Обьект для детального описания исключения
    */
    public static ErrorInfo getErrorInfo(HttpServletRequest request,
                                  Exception exception,
                                  ErrorType errorType,
                                  String errorMsg,
                                  Map details) {
        log.info("Create ErrorInfo for Request: [requestUrl={}], [exception={}]",
                request.getRequestURL(), exception.toString());

        Throwable rootCause = getRootCause(exception);

        return new ErrorInfo(
                request.getRequestURL().toString(),
                errorType,
                errorMsg,
                details != null && details.size() > 0 ? details :
                        Map.of(Const.MSG, getExceptionMessage(rootCause)));
    }

    public static ErrorInfo getErrorInfo(HttpServletRequest request,
                                         Exception exception,
                                         ErrorType errorType,
                                         String errorMsg){
        return getErrorInfo(request, exception, errorType, errorMsg, null);
    }

    public static boolean isSQLException(Exception ex, String sqlError){
        log.info("Check Exception for SQL Errors [exception={}], [sql error={}]", ex.toString(), sqlError);

        String rootMsg = getRootCause(ex).getMessage();

        return rootMsg != null && rootMsg.toLowerCase().contains(sqlError);
    }

    public static String getNameFieldForSqlException(Exception ex){
        log.info("Check Name Field for SQL Exception [exception={}]]", ex.toString());

        String rootMsg = getRootCause(ex).getMessage();

        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.CARTRIDGE_REFERENCES)){
            return Const.CARTRIDGE_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.TONER_REFERENCES)){
            return Const.TONER_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.DRUM_REFERENCES)){
            return Const.DRUM_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.MAGNETIC_SHAFT_REFERENCES)){
            return Const.MAGNETIC_SHAFT_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.PRIMARY_CHARGE_SHAFT_REFERENCES)){
            return Const.PRIMARY_CHARGE_SHAFT_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.CLEANING_BLADE_REFERENCES)){
            return Const.CLEANING_BLADE_FIELD;
        }
        if(rootMsg != null && rootMsg.toLowerCase().contains(Const.DISPENSING_BLADE_REFERENCES)){
            return Const.DISPENSING_BLADE_FIELD;
        }

        return Const.MSG;
    }
}
