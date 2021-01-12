package ua.notky.cartridge.consumables.web.model.parts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.service.model.parts.primarychargeshaft.PrimaryChargeShaftService;
import ua.notky.cartridge.consumables.util.JsonUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.*;
import static ua.notky.cartridge.consumables.tools.web.WebTool.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;

public class PrimaryChargeShaftControllerTest extends AbstractControllerTest {

    @BeforeAll
    static void prepareForTest(){
        url = ConstUrl.UI_PRIMARY_CHARGE_SHAFT;
    }

    @Autowired
    private PrimaryChargeShaftService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(PrimaryChargeShaft.class, PRIMARY_CHARGE_SHAFTS));
    }

    @Test
    void get() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl(ID_PRIMARY_CHARGE_SHAFT_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(PrimaryChargeShaft.class, PRIMARY_CHARGE_SHAFT_2));
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
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_PRIMARY_CHARGE_SHAFT_5)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertIterableEquals(service.getAll(),
                Arrays.asList(PRIMARY_CHARGE_SHAFT_1, PRIMARY_CHARGE_SHAFT_2, PRIMARY_CHARGE_SHAFT_3, PRIMARY_CHARGE_SHAFT_4));
    }

    @Test
    void deleteHasDependency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_PRIMARY_CHARGE_SHAFT_2)))
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
                .andExpect(status().isNoContent());
    }

    @Test
    void createInvalid() throws  Exception {
        PrimaryChargeShaft shaftBig = new PrimaryChargeShaft(INVALID_NAME_BIG);

        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(shaftBig)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }

    @Test
    void createDuplicate() throws  Exception {
        PrimaryChargeShaft newShaft = getNew();
        newShaft.setName(PRIMARY_CHARGE_SHAFT_2.getName());
        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newShaft)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }
}
