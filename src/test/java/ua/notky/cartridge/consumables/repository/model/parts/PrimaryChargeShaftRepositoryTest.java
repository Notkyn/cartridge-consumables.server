package ua.notky.cartridge.consumables.repository.model.parts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.parts.primarychargeshaft.PrimaryChargeShaftRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.parts.PrimaryChargeShaftTool.*;

public class PrimaryChargeShaftRepositoryTest extends AbstractTestRepository {
    @Autowired
    private PrimaryChargeShaftRepository repository;

    @Test
    @Transactional
    void save() {
        PrimaryChargeShaft newPrimaryChargeShaft = getNew();

        PrimaryChargeShaft result = repository.save(newPrimaryChargeShaft);
        assertEquals(newPrimaryChargeShaft, result);

        int newPrimaryChargeShaftId = result.getId();
        assertEquals(repository.getById(newPrimaryChargeShaftId), newPrimaryChargeShaft);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_PRIMARY_CHARGE_SHAFT_2), PRIMARY_CHARGE_SHAFT_2);
        assertNotEquals(repository.getById(ID_PRIMARY_CHARGE_SHAFT_2), PRIMARY_CHARGE_SHAFT_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridges() {
        assertIterableEquals(repository.getWithCartridges(ID_PRIMARY_CHARGE_SHAFT_2).getCartridges(),
                Collections.singletonList(CARTRIDGE_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_PRIMARY_CHARGE_SHAFT_5));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_PRIMARY_CHARGE_SHAFT_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(PRIMARY_CHARGE_SHAFT_1, PRIMARY_CHARGE_SHAFT_2, PRIMARY_CHARGE_SHAFT_3, PRIMARY_CHARGE_SHAFT_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), PRIMARY_CHARGE_SHAFTS);
    }
}
