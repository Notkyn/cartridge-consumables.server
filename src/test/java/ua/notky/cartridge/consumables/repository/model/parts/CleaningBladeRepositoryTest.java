package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.cleaningblade.CleaningBladeRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.CleaningBladeTool.*;

public class CleaningBladeRepositoryTest extends AbstractTestRepository {
    @Autowired
    private CleaningBladeRepository repository;

    @Test
    @Transactional
    void save() {
        CleaningBlade newCleaningBlade = getNew();

        CleaningBlade result = repository.save(newCleaningBlade);
        assertEquals(newCleaningBlade, result);

        int newCleaningBladeId = result.getId();
        assertEquals(repository.getById(newCleaningBladeId), newCleaningBlade);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_CLEANING_BLADE_2), CLEANING_BLADE_2);
        assertNotEquals(repository.getById(ID_CLEANING_BLADE_2), CLEANING_BLADE_3);
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
        assertTrue(repository.delete(ID_CLEANING_BLADE_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_CLEANING_BLADE_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(CLEANING_BLADE_1, CLEANING_BLADE_2, CLEANING_BLADE_3, CLEANING_BLADE_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), CLEANING_BLADES);
    }
}
