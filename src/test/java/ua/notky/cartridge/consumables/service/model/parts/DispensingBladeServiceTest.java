package ua.notky.cartridge.consumables.service.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.parts.dispensingblade.DispensingBladeService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.*;

public class DispensingBladeServiceTest extends AbstractTestService {
    @Autowired
    private DispensingBladeService service;

    @Test
    @Transactional
    void create() {
        DispensingBlade newDispensingBlade = getNew();

        DispensingBlade result = service.create(newDispensingBlade);
        assertEquals(newDispensingBlade, result);

        int newDispensingBladeId = result.getId();
        assertEquals(service.get(newDispensingBladeId), newDispensingBlade);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(DISPENSING_BLADE_2)));
    }

    @Test
    void update() {
        DispensingBlade updated = getUpdated(DISPENSING_BLADE_2);

        assertEquals(service.update(updated), updated);
        assertEquals(service.get(updated.getId()), updated);
    }

    @Test
    @Transactional
    void updateIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void updateIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.update(getNew()));
    }

    @Test
    void get() {
        assertEquals(service.get(ID_DISPENSING_BLADE_2), DISPENSING_BLADE_2);
        assertNotEquals(service.get(ID_DISPENSING_BLADE_2), DISPENSING_BLADE_3);
    }

    @Test
    void getWithCartridges(){
        assertIterableEquals(service.getWithCartridges(ID_DISPENSING_BLADE_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_DISPENSING_BLADE_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(DISPENSING_BLADE_1, DISPENSING_BLADE_2, DISPENSING_BLADE_3, DISPENSING_BLADE_4));
    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_DISPENSING_BLADE_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), DISPENSING_BLADES);
    }
}
