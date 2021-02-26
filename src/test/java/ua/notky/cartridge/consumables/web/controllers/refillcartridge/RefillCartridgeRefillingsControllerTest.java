package ua.notky.cartridge.consumables.web.controllers.refillcartridge;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.service.model.refillcartridge.RefillCartridgeService;
import ua.notky.cartridge.consumables.util.JsonUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.notky.cartridge.consumables.tools.data.model.RefillCartridgeTool.*;
import static ua.notky.cartridge.consumables.tools.web.WebTool.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;

class RefillCartridgeRefillingsControllerTest extends AbstractControllerTest {
    @BeforeAll
    static void prepareForTest(){
        url = ConstUrl.UI_REFILL_REFILLINGS;
    }

    @Autowired
    private RefillCartridgeService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(RefillCartridge.class, REFILLS_CARTRIDGES));
    }

    @Test
    void get() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl(ID_REFILL_CARTRIDGE_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(RefillCartridge.class, service.get(ID_REFILL_CARTRIDGE_2)));
    }

    @Test
    void getNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl(INVALID_ID)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(DATA_NOT_FOUND));
    }

    @Test
    void delete() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_REFILL_CARTRIDGE_5)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertIterableEquals(service.getAll(),
                Arrays.asList(REFILL_CARTRIDGE_1, REFILL_CARTRIDGE_2, REFILL_CARTRIDGE_3, REFILL_CARTRIDGE_4));
    }

    @Test
    void deleteNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(INVALID_ID)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(DATA_NOT_FOUND));
    }

    @Test
    void create() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getNew())))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void createDuplicate() throws  Exception {
        RefillCartridge newRefillCartridge = getNew();
        newRefillCartridge.setDate(REFILL_CARTRIDGE_2.getDate());
        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRefillCartridge)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }
}