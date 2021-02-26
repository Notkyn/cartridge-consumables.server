package ua.notky.cartridge.consumables.web.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.service.model.cartridge.CartridgeService;
import ua.notky.cartridge.consumables.util.JsonUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.*;
import static ua.notky.cartridge.consumables.tools.web.WebTool.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;

public class CartridgeControllerTest extends AbstractControllerTest {
    @BeforeAll
    static void prepareForTest(){
        url = ConstUrl.UI_CARTRIDGE;
    }

    @Autowired
    private CartridgeService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(Cartridge.class, CARTRIDGES));
    }

    @Test
    void get() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl(ID_CARTRIDGE_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(Cartridge.class, CARTRIDGE_2));
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
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_CARTRIDGE_5)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertIterableEquals(service.getAll(),
                Arrays.asList(CARTRIDGE_1, CARTRIDGE_2, CARTRIDGE_3, CARTRIDGE_4));
    }

    @Test
    void deleteHasDependency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_CARTRIDGE_2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(HAS_DEPENDENCY));
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
    void createInvalidName() throws  Exception {
        Cartridge cartridgeBig = new Cartridge(INVALID_NAME_BIG);

        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(cartridgeBig)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }

    @Test
    void createInvalidCoef() throws  Exception {
        Cartridge cartridgeNegativCoef = getNew();
        cartridgeNegativCoef.setCoefToner(CARTIDGE_COEF_INVALID);

        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(cartridgeNegativCoef)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));

    }

    @Test
    void createInvalidAllParts() throws  Exception {
        Cartridge cartridgeNullParts = getNew();
        cartridgeNullParts.setToner(null);
        cartridgeNullParts.setDrum(null);
        cartridgeNullParts.setMagneticShaft(null);
        cartridgeNullParts.setPrimaryChargeShaft(null);
        cartridgeNullParts.setCleaningBlade(null);
        cartridgeNullParts.setDispensingBlade(null);

        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(cartridgeNullParts)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }

    @Test
    void createDuplicate() throws  Exception {
        Cartridge newCartridge = getNew();
        newCartridge.setName(CARTRIDGE_2.getName());
        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCartridge)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }

}
