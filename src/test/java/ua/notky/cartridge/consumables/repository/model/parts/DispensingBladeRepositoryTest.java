package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.dispensingblade.DispensingBladeRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.DispensingBladeTool.*;

public class DispensingBladeRepositoryTest extends AbstractTestRepository {
    @Autowired
    private DispensingBladeRepository repository;

    @Test
    @Transactional
    void save() {
        DispensingBlade newDispensingBlade = getNew();

        DispensingBlade result = repository.save(newDispensingBlade);
        assertEquals(newDispensingBlade, result);

        int newDispensingBladeId = result.getId();
        assertEquals(repository.getById(newDispensingBladeId), newDispensingBlade);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_DISPENSING_BLADE_2), DISPENSING_BLADE_2);
        assertNotEquals(repository.getById(ID_DISPENSING_BLADE_2), DISPENSING_BLADE_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridges() {
        assertIterableEquals(repository.getWithCartridges(ID_DISPENSING_BLADE_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_DISPENSING_BLADE_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_DISPENSING_BLADE_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(DISPENSING_BLADE_1, DISPENSING_BLADE_2, DISPENSING_BLADE_3, DISPENSING_BLADE_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), DISPENSING_BLADES);
    }
}
