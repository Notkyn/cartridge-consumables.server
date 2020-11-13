package ua.notky.cartridge.consumables.repository.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.WorkingDay;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.workingday.WorkingDayRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_2;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_4;
import static ua.notky.cartridge.consumables.tools.data.model.WorkingDayTool.*;

public class WorkingDayRepositoryTest extends AbstractTestRepository {
    @Autowired
    private WorkingDayRepository repository;

    @Test
    @Transactional
    void save() {
        WorkingDay newWorkingDay = getNew();

        WorkingDay result = repository.save(newWorkingDay);
        assertEquals(newWorkingDay, result);

        int newWorkingDayId = result.getId();
        assertEquals(repository.getById(newWorkingDayId), newWorkingDay);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_WORKING_DAY_2), WORKING_DAY_2);
        assertNotEquals(repository.getById(ID_WORKING_DAY_2), WORKING_DAY_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithDepartments(){
        WorkingDay workingDay = repository.getWithDepartments(ID_WORKING_DAY_2);
        assertEquals(workingDay, WORKING_DAY_2);
        assertIterableEquals(workingDay.getDepartments(),
                List.of(DEPARTMENT_2, DEPARTMENT_4));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(WORKING_DAY_5));
        assertNull(repository.getById(ID_WORKING_DAY_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(WORKING_DAY_1, WORKING_DAY_2, WORKING_DAY_3, WORKING_DAY_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), WORKING_DAYS);
    }
}
