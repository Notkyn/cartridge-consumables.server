package ua.notky.cartridge.consumables.service.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.*;

public class CleaningBladeServiceTest extends AbstractTestService {
    @Autowired
    private CleaningBladeService service;

    @Test
    @Transactional
    void create() {
        CleaningBlade newCleaningBlade = getNew();

        CleaningBlade result = service.create(newCleaningBlade);
        assertEquals(newCleaningBlade, result);

        int newCleaningBladeId = result.getId();
        assertEquals(service.get(newCleaningBladeId), newCleaningBlade);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(CLEANING_BLADE_2)));
    }

    @Test
    void update() {
        CleaningBlade updated = getUpdated(CLEANING_BLADE_2);

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
        assertEquals(service.get(ID_CLEANING_BLADE_2), CLEANING_BLADE_2);
        assertNotEquals(service.get(ID_CLEANING_BLADE_2), CLEANING_BLADE_3);
    }

    @Test
    void getWithCartridges(){
        assertIterableEquals(service.getWithCartridges(ID_CLEANING_BLADE_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_CLEANING_BLADE_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(CLEANING_BLADE_1, CLEANING_BLADE_2, CLEANING_BLADE_3, CLEANING_BLADE_4));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_CLEANING_BLADE_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), CLEANING_BLADES);
    }
}
