package ua.notky.cartridge.consumables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.notky.cartridge.consumables.configuration.ServerConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties
public class ConsumablesApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumablesApplication.class);
    private ServerConfiguration configuration;

    @PostConstruct
    public void init() {
        LOGGER.info("Init Global Properties: [timezone={}]", configuration.getTimeZone());
        TimeZone.setDefault(TimeZone.getTimeZone(configuration.getTimeZone()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumablesApplication.class, args);

        LOGGER.info("Application is running...");

    }

    @Autowired
    public void setConfiguration(ServerConfiguration configuration) {
        this.configuration = configuration;
    }
}
