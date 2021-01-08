package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ua.notky.cartridge.consumables.util.constant.Const;
import ua.notky.cartridge.consumables.util.exception.ErrorType;

@Slf4j
@Component
public class MessageI18nUtil {
    private final MessageSource messageSource;

    @Autowired
    public MessageI18nUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, String... args) {
        log.info("Get Message for Code: [code={}]", code);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public String getMessageBindFormat(String code){
        log.info("Get Message with Bind Format for Code: [code={}]", code);
        return getMessage(String.format(Const.BIND_CODE_FORMAT, code.toLowerCase()));
    }

    public String getMessageErrorType(ErrorType errorType){
        log.info("Get Message Error Type: [errorType={}]", errorType);
        return getMessage(errorType.getErrorCode());
    }
}
