package ua.notky.cartridge.consumables.configuration.web.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ua.notky.cartridge.consumables.configuration.WebConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    Интерсептор который пропускает запросы JS для тестов на прокси сервер
 */
//todo убрать
@Component
public class AccessAjaxInterceptor implements HandlerInterceptor {
    private WebConfig webConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getHeader("host").equals(webConfig.getHost())){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
        }
        return true;
    }

    @Autowired
    public void setWebConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }
}