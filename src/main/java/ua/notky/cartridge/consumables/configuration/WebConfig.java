package ua.notky.cartridge.consumables.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ua.notky.cartridge.consumables.configuration.convertors.LocalDateFormatter;
import ua.notky.cartridge.consumables.configuration.web.JacksonObjectMapper;
import ua.notky.cartridge.consumables.configuration.web.interceptors.AccessAjaxInterceptor;

import java.util.Locale;

@Configuration
@PropertySource("classpath:properties/global.properties")
public class WebConfig implements WebMvcConfigurer {
    private AccessAjaxInterceptor accessAjaxInterceptor;

    @Value("${host}")
    private String host;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessAjaxInterceptor);
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(localDateFormatter());
    }

    // Beans...

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(JacksonObjectMapper mapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(mapper);

        return jsonConverter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        Locale defaultLocale = new Locale("ru");
        slr.setDefaultLocale(defaultLocale);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public LocalDateFormatter localDateFormatter(){
        return new LocalDateFormatter();
    }

    // Setters...

    @Autowired
    public void setAccessAjaxInterceptor(AccessAjaxInterceptor accessAjaxInterceptor) {
        this.accessAjaxInterceptor = accessAjaxInterceptor;
    }

    // Getters...

    public String getHost() {
        return host;
    }
}
