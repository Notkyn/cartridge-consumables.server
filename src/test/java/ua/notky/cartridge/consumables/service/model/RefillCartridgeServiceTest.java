package ua.notky.cartridge.consumables.service.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.RefillCartridge;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.refillcartridge.RefillCartridgeService;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_3;
import static ua.notky.cartridge.consumables.tools.data.model.RefillCartridgeTool.*;

public class RefillCartridgeServiceTest extends AbstractTestService {
    @Autowired
    private RefillCartridgeService service;

    @Test
    @Transactional
    void create() {
        RefillCartridge newRefillCartridge = getNew();

        RefillCartridge result = service.create(newRefillCartridge);
        assertEquals(newRefillCartridge, result);

        int newWorkingDayId = result.getId();
        assertEquals(service.get(newWorkingDayId), newRefillCartridge);
    }

    @Test
    @Transactional
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(REFILL_CARTRIDGE_2)));
    }

    @Test
    @Transactional
    void update() {
        RefillCartridge updated = getUpdated(REFILL_CARTRIDGE_2);

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
        assertEquals(service.get(ID_REFILL_CARTRIDGE_2), REFILL_CARTRIDGE_2);
        assertNotEquals(service.get(ID_REFILL_CARTRIDGE_2), REFILL_CARTRIDGE_3);
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_REFILL_CARTRIDGE_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(REFILL_CARTRIDGE_1, REFILL_CARTRIDGE_2, REFILL_CARTRIDGE_3, REFILL_CARTRIDGE_4));
    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), REFILLS_CARTRIDGES);
    }

    @Test
    void getAllDates(){
        List<LocalDate> dates = service.getAllDates();
        Collections.sort(dates);
        assertIterableEquals(dates, REFILLS_DATES);
    }

    @Test
    void getAllByDateWithDepartment(){
        List<RefillCartridge> refillings = service.getAllByDateWithDepartment(DATE_REFILL_3);
        assertIterableEquals(refillings, List.of(REFILL_CARTRIDGE_3));
        assertEquals(refillings.size(), 1);
        assertEquals(refillings.get(0).getDepartment(), DEPARTMENT_3);

        assertEquals(service.getAllByDateWithDepartment(INVALID_DATE_REFILL).size(), 0);
    }
}
