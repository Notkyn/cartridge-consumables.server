package ua.notky.cartridge.consumables.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:properties/global.properties")
public class ServerConfiguration {

    @Value("${app.timezone}")
    private String timeZone;


    public String getTimeZone() {
        return timeZone;
    }
}
