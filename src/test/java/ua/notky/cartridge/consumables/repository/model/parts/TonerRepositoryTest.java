package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.Toner;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.toner.TonerRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.TonerTool.*;

public class TonerRepositoryTest extends AbstractTestRepository {
    @Autowired
    private TonerRepository repository;

    @Test
    @Transactional
    void save() {
        Toner newToner = getNew();

        Toner result = repository.save(newToner);
        assertEquals(newToner, result);

        int newTonerId = result.getId();
        assertEquals(repository.getById(newTonerId), newToner);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_TONER_2), TONER_2);
        assertNotEquals(repository.getById(ID_TONER_2), TONER_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridges() {
        assertIterableEquals(repository.getWithCartridges(ID_TONER_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_TONER_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_TONER_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(TONER_1, TONER_2, TONER_3, TONER_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), TONERS);
    }
}
