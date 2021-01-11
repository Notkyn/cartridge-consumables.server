package ua.notky.cartridge.consumables.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:db/data.sql",
        executionPhase = BEFORE_TEST_METHOD,
        config = @SqlConfig(encoding = "UTF-8"))
public class AbstractControllerTest {

    @Autowired
    protected MockMvc mvc;

    protected static String url;

    protected String generateUrl(int... args){
        if(args.length == 0){
            return url;
        } else {
            StringBuilder sb = new StringBuilder(url);

            for(int item : args){
                sb.append("/")
                    .append(item);
            }

            return sb.toString();
        }
    }
}
