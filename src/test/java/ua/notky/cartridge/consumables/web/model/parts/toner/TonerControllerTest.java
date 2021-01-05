package ua.notky.cartridge.consumables.web.model.parts.toner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.service.model.parts.toner.TonerService;
import ua.notky.cartridge.consumables.util.JsonUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.*;
import static ua.notky.cartridge.consumables.tools.web.WebTool.*;

public class TonerControllerTest extends AbstractControllerTest {

    @Autowired
    private TonerService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ConstUrl.UI_TONER))
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(Toner.class, TONERS));
    }

    @Test
    void get() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.get(ConstUrl.UI_TONER + "/" + ID_TONER_2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(Toner.class, TONER_2));
    }

    @Test
    void getNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ConstUrl.UI_TONER + "/" + INVALID_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.delete(ConstUrl.UI_TONER + "/" + ID_TONER_5))
                .andExpect(status().isNoContent());

        assertIterableEquals(service.getAll(),
                Arrays.asList(TONER_1, TONER_2, TONER_3, TONER_4));
    }

    @Test
    void deleteHasDependency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(ConstUrl.UI_TONER + "/" + ID_TONER_2))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(ConstUrl.UI_TONER + "/" + INVALID_ID))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void create() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.post(ConstUrl.UI_TONER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getNew())))
                .andExpect(status().isNoContent());
    }

    @Test
    void createInvalid() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.post(ConstUrl.UI_TONER)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createDuplicate() throws  Exception {
        Toner newToner = getNew();
        newToner.setName(TONER_2.getName());
        mvc.perform(MockMvcRequestBuilders.post(ConstUrl.UI_TONER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newToner)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}