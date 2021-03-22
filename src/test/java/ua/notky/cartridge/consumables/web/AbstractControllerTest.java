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

    protected String generateUrl(){
        return url;
    }

    protected <T> String generateUrl(T arg) {
        if(arg == null) {
            return generateUrl();
        }
        return createUrl(generateUrl(), arg);
    }

    protected <T> String generateUrl(String path, T arg) {
        String tempUrl;
        if(arg == null) {
            tempUrl = generateUrl();
        } else {
            tempUrl = createUrl(generateUrl(), path);
        }

        if(arg != null) {
            tempUrl = createUrl(tempUrl, arg);
        }

        return tempUrl;
    }

    private <T> String createUrl(String url, T arg){
        String[] argItems = String.valueOf(arg).split("/");

        StringBuilder sb = new StringBuilder(url);
        for(String item : argItems){
            sb.append("/").append(item);
        }
        return sb.toString();
    }
}
