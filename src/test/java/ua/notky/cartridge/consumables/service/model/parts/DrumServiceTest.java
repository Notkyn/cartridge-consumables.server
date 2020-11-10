package ua.notky.cartridge.consumables.service.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.parts.drum.DrumService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.*;

public class DrumServiceTest extends AbstractTestService {
    @Autowired
    private DrumService service;

    @Test
    @Transactional
    void create() {
        Drum newDrum = getNew();

        Drum result = service.create(newDrum);
        assertEquals(newDrum, result);

        int newDrumId = result.getId();
        assertEquals(service.get(newDrumId), newDrum);
    }

    @Test
    @Transactional
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(DRUM_2)));
    }

    @Test
    @Transactional
    void update() {
        Drum updated = getUpdated(DRUM_2);

        assertEquals(service.update(updated), updated);
        assertEquals(service.get(updated.getId()), updated);
    }

    @Test
    @Transactional
    void updateIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.update(null));
    }

    @Test
    @Transactional
    void updateIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.update(getNew()));
    }

    @Test
    void get() {
        assertEquals(service.get(ID_DRUM_2), DRUM_2);
        assertNotEquals(service.get(ID_DRUM_2), DRUM_3);
    }

    @Test
    void getWithCartridges(){
        assertIterableEquals(service.getWithCartridges(ID_DRUM_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_DRUM_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(DRUM_1, DRUM_2, DRUM_3, DRUM_4));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_DRUM_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), DRUMS);
    }
}
