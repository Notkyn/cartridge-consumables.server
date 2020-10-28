package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.magneticshaft.MagneticShaftRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.MagneticShaftTool.*;

public class MagneticShaftRepositoryTest extends AbstractTestRepository {
    @Autowired
    private MagneticShaftRepository repository;

    @Test
    @Transactional
    void save() {
        MagneticShaft newMagneticShaft = getNew();

        MagneticShaft result = repository.save(newMagneticShaft);
        assertEquals(newMagneticShaft, result);

        int newMagneticShaftId = result.getId();
        assertEquals(repository.getById(newMagneticShaftId), newMagneticShaft);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_MAGNETIC_SHAFT_2), MAGNETIC_SHAFT_2);
        assertNotEquals(repository.getById(ID_MAGNETIC_SHAFT_2), MAGNETIC_SHAFT_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridges() {
        assertIterableEquals(repository.getWithCartridges(ID_MAGNETIC_SHAFT_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_MAGNETIC_SHAFT_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_MAGNETIC_SHAFT_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(MAGNETIC_SHAFT_1, MAGNETIC_SHAFT_2, MAGNETIC_SHAFT_3, MAGNETIC_SHAFT_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), MAGNETIC_SHAFTS);
    }
}
