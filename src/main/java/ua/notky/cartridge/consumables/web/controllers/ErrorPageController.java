package ua.notky.cartridge.consumables.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.notky.cartridge.consumables.util.ExceptionUtil;
import ua.notky.cartridge.consumables.util.MessageI18nUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static ua.notky.cartridge.consumables.util.exception.ErrorType.APP_ERROR;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.WRONG_REQUEST;

@Controller
public class ErrorPageController implements ErrorController {
    private MessageI18nUtil messageI18nUtil;

    @Autowired
    public void setMessageI18nUtil(MessageI18nUtil messageI18nUtil) {
        this.messageI18nUtil = messageI18nUtil;
    }


    @RequestMapping(ConstUrl.PAGE_ERROR)
    public ModelAndView handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null && Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()){

//            System.out.println("Uri: " + request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
//            System.out.println("Msg: " + request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

            return ExceptionUtil.getErrorPage(HttpStatus.NOT_FOUND.value(),
                    messageI18nUtil.getMessageErrorType(WRONG_REQUEST));
        }

        return ExceptionUtil.getErrorPage(500, messageI18nUtil.getMessageErrorType(APP_ERROR));
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
