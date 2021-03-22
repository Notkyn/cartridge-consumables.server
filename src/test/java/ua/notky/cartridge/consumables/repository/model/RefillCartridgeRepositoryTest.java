package ua.notky.cartridge.consumables.repository.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.refillcartridge.RefillCartridgeRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_3;
import static ua.notky.cartridge.consumables.tools.data.model.RefillCartridgeTool.*;

public class RefillCartridgeRepositoryTest extends AbstractTestRepository {
    @Autowired
    private RefillCartridgeRepository repository;

    @Test
    @Transactional
    void save() {
        RefillCartridge newRefillCartridge = getNew();

        RefillCartridge result = repository.save(newRefillCartridge);
        assertEquals(newRefillCartridge, result);

        int newWorkingDayId = result.getId();
        assertEquals(repository.getById(newWorkingDayId), newRefillCartridge);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_REFILL_CARTRIDGE_2), REFILL_CARTRIDGE_2);
        assertNotEquals(repository.getById(ID_REFILL_CARTRIDGE_2), REFILL_CARTRIDGE_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(REFILL_CARTRIDGE_5));
        assertNull(repository.getById(ID_REFILL_CARTRIDGE_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(REFILL_CARTRIDGE_1, REFILL_CARTRIDGE_2, REFILL_CARTRIDGE_3, REFILL_CARTRIDGE_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), REFILLS_CARTRIDGES);
    }

    @Test
    void getAllDates(){
        List<LocalDate> dates = repository.getAllDates();
        Collections.sort(dates);
        assertIterableEquals(dates, REFILLS_DATES);
    }

    @Test
    void getAllByDateWithDepartment(){
        List<RefillCartridge> refillings = repository.getAllByDateWithDepartment(DATE_REFILL_3);
        assertIterableEquals(refillings, List.of(REFILL_CARTRIDGE_3));
        assertEquals(refillings.size(), 1);
        assertEquals(refillings.get(0).getDepartment(), DEPARTMENT_3);

        assertEquals(repository.getAllByDateWithDepartment(INVALID_DATE_REFILL).size(), 0);
    }
}
