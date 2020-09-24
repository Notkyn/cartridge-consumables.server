package ua.notky.cartridge.consumables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumablesApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumablesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumablesApplication.class, args);

        LOGGER.info("Application is running...");

    }

}
