package ua.notky.cartridge.consumables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.notky.cartridge.consumables.configuration.ServerConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class ConsumablesApplication {
    private ServerConfiguration configuration;

    @PostConstruct
    public void init() {
        log.info("Init Global Properties: [timezone={}]", configuration.getTimeZone());
        TimeZone.setDefault(TimeZone.getTimeZone(configuration.getTimeZone()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumablesApplication.class, args);

        log.debug("Application is running...");

    }

    @Autowired
    public void setConfiguration(ServerConfiguration configuration) {
        this.configuration = configuration;
    }
}
