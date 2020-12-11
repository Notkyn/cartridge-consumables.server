package ua.notky.cartridge.consumables.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.notky.cartridge.consumables.configuration.web.JacksonObjectMapper;
import ua.notky.cartridge.consumables.configuration.web.interceptors.AccessAjaxInterceptor;

@Configuration
@PropertySource("classpath:properties/global.properties")
public class WebConfig implements WebMvcConfigurer {
    private AccessAjaxInterceptor accessAjaxInterceptor;

    @Value("${host}")
    private String host;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessAjaxInterceptor);
    }

    // Beans...

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(JacksonObjectMapper mapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(mapper);

        return jsonConverter;
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
