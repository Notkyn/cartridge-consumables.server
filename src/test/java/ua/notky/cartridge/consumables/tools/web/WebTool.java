package ua.notky.cartridge.consumables.tools.web;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import ua.notky.cartridge.consumables.model.AbstractBaseEntity;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static ua.notky.cartridge.consumables.tools.web.JsonTool.readListFromJsonMvcResult;

public class WebTool {

    public static ResultMatcher headerContentType(){
        return header().string("Content-Type", "application/json");
    }

    public static ResultMatcher contentContentType(){
        return content().contentType(MediaType.APPLICATION_JSON);
    }

    @SafeVarargs
    public static <T extends AbstractBaseEntity> ResultMatcher bodyJson(Class<T> cl, T... expected) {
        return bodyJson(cl, List.of(expected));
    }

    public static <T extends AbstractBaseEntity> ResultMatcher bodyJson(Class<T> cl, Iterable<T> expected) {
        return mvcResult -> assertIterableEquals(readListFromJsonMvcResult(mvcResult, cl), expected);
    }
}
