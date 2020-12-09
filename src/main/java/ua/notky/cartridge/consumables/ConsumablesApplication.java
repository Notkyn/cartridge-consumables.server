package ua.notky.cartridge.consumables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ua.notky.cartridge.consumables.configuration.ServerConfig;
import ua.notky.cartridge.consumables.repository.department.DepartmentRepository;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;
import ua.notky.cartridge.consumables.service.model.department.DepartmentService;
import ua.notky.cartridge.consumables.service.model.workingday.WorkingDayService;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class ConsumablesApplication {
    private ServerConfig configuration;

    @PostConstruct
    public void init() {
        log.info("Init Global Properties: [timezone={}]", configuration.getTimeZone());
        TimeZone.setDefault(TimeZone.getTimeZone(configuration.getTimeZone()));
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConsumablesApplication.class, args);

        log.debug("Application is running...");

        DepartmentService departmentService = ctx.getBean(DepartmentService.class);
        DepartmentRepository departmentRepository = ctx.getBean(DepartmentRepository.class);
        WorkingDayService workingDayService = ctx.getBean(WorkingDayService.class);
        CartridgeService cartridgeService = ctx.getBean(CartridgeService.class);

    }

    @Autowired
    public void setConfiguration(ServerConfig configuration) {
        this.configuration = configuration;
    }
}
