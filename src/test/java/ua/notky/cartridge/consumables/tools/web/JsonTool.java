package ua.notky.cartridge.consumables.tools.web;

import org.springframework.test.web.servlet.MvcResult;
import org.thymeleaf.spring5.expression.Mvc;
import ua.notky.cartridge.consumables.model.AbstractBaseEntity;
import ua.notky.cartridge.consumables.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.List;

public class JsonTool {

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();

    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValues(getContent(result), clazz);
    }
}
