package ua.notky.cartridge.consumables.repository.cartridge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Cartridge;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.CartridgeTool.*;

public class CartridgeRepositoryTest extends AbstractTestRepository {
    @Autowired
    private CartridgeRepository repository;

    @Test
    @Transactional
    void save() {
        Cartridge newDepartmetn = getNew();

        Cartridge result = repository.save(newDepartmetn);
        assertEquals(newDepartmetn, result);

        int newDepartmentId = result.getId();
        assertEquals(repository.getById(newDepartmentId), newDepartmetn);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_CARTRIDGE_2), CARTRIDGE_2);
        assertNotEquals(repository.getById(ID_CARTRIDGE_2), CARTRIDGE_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_CARTRIDGE_2));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_CARTRIDGE_2));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(CARTRIDGE_1, CARTRIDGE_3, CARTRIDGE_4, CARTRIDGE_5));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), CARTRIDGES);
    }
}
