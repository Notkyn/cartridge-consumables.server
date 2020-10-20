package ua.notky.cartridge.consumables.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.notky.cartridge.consumables.tools.extension.TimingExtension;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/data.sql",
        executionPhase = BEFORE_TEST_METHOD,
        config = @SqlConfig(encoding = "UTF-8"))
public class AbstractTestService {
}
