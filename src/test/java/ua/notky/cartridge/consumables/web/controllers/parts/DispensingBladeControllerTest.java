package ua.notky.cartridge.consumables.web.controllers.parts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.service.model.parts.dispensingblade.DispensingBladeService;
import ua.notky.cartridge.consumables.util.JsonUtil;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;
import ua.notky.cartridge.consumables.web.AbstractControllerTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_NAME_BIG;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.*;
import static ua.notky.cartridge.consumables.tools.web.WebTool.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.*;
import static ua.notky.cartridge.consumables.util.exception.ErrorType.VALIDATION_ERROR;

public class DispensingBladeControllerTest extends AbstractControllerTest {
    @BeforeAll
    static void prepareForTest(){
        url = ConstUrl.UI_PARTS_DISPENSING_BLADE;
    }

    @Autowired
    private DispensingBladeService service;

    @Test
    void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(DispensingBlade.class, DISPENSING_BLADES));
    }

    @Test
    void get() throws  Exception {
        mvc.perform(MockMvcRequestBuilders.get(generateUrl(ID_DISPENSING_BLADE_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(bodyJson(DispensingBlade.class, DISPENSING_BLADE_2));
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
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_DISPENSING_BLADE_5)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertIterableEquals(service.getAll(),
                Arrays.asList(DISPENSING_BLADE_1, DISPENSING_BLADE_2, DISPENSING_BLADE_3, DISPENSING_BLADE_4));
    }

    @Test
    void deleteHasDependency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(generateUrl(ID_DISPENSING_BLADE_2)))
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
        DispensingBlade bladeBig = new DispensingBlade(INVALID_NAME_BIG);

        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(bladeBig)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }

    @Test
    void createDuplicate() throws  Exception {
        DispensingBlade newBlade = getNew();
        newBlade.setName(DISPENSING_BLADE_2.getName());
        mvc.perform(MockMvcRequestBuilders.post(generateUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newBlade)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(headerContentType())
                .andExpect(contentContentType())
                .andExpect(matchTypeError(VALIDATION_ERROR));
    }
}
