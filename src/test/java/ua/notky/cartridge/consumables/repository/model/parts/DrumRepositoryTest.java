package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.drum.DrumRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.ID_CLEANING_BLADE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DrumTool.*;

public class DrumRepositoryTest extends AbstractTestRepository {
    @Autowired
    private DrumRepository repository;

    @Test
    @Transactional
    void save() {
        Drum newDrum = getNew();

        Drum result = repository.save(newDrum);
        assertEquals(newDrum, result);

        int newDrumId = result.getId();
        assertEquals(repository.getById(newDrumId), newDrum);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_DRUM_2), DRUM_2);
        assertNotEquals(repository.getById(ID_CLEANING_BLADE_2), DRUM_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridges() {
        assertIterableEquals(repository.getWithCartridges(ID_CLEANING_BLADE_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_DRUM_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_DRUM_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(DRUM_1, DRUM_2, DRUM_3, DRUM_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), DRUMS);
    }
}
