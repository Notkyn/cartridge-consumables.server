package ua.notky.cartridge.consumables.service.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.parts.magneticshaft.MagneticShaftService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.*;

public class MagneticShaftServiceTest extends AbstractTestService {
    @Autowired
    private MagneticShaftService service;

    @Test
    @Transactional
    void create() {
        MagneticShaft newMagneticShaft = getNew();

        MagneticShaft result = service.create(newMagneticShaft);
        assertEquals(newMagneticShaft, result);

        int newMagneticShaftId = result.getId();
        assertEquals(service.get(newMagneticShaftId), newMagneticShaft);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(MAGNETIC_SHAFT_2)));
    }

    @Test
    void update() {
        MagneticShaft updated = getUpdated(MAGNETIC_SHAFT_2);

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
        assertEquals(service.get(ID_MAGNETIC_SHAFT_2), MAGNETIC_SHAFT_2);
        assertNotEquals(service.get(ID_MAGNETIC_SHAFT_2), MAGNETIC_SHAFT_3);
    }

    @Test
    void getWithCartridges(){
        assertIterableEquals(service.getWithCartridges(ID_MAGNETIC_SHAFT_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_MAGNETIC_SHAFT_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(MAGNETIC_SHAFT_1, MAGNETIC_SHAFT_2, MAGNETIC_SHAFT_3, MAGNETIC_SHAFT_4));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_MAGNETIC_SHAFT_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), MAGNETIC_SHAFTS);
    }
}
