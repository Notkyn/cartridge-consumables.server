package ua.notky.cartridge.consumables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ua.notky.cartridge.consumables.configuration.ServerConfiguration;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;
import ua.notky.cartridge.consumables.service.model.parts.cleaningblade.CleaningBladeService;

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
        ConfigurableApplicationContext ctx = SpringApplication.run(ConsumablesApplication.class, args);

        log.debug("Application is running...");

        CartridgeService cartridgeService = ctx.getBean(CartridgeService.class);
        CleaningBladeService cleaningBladeService = ctx.getBean(CleaningBladeService.class);

        cartridgeService.delete(2);

    }

    @Autowired
    public void setConfiguration(ServerConfiguration configuration) {
        this.configuration = configuration;
    }
}
