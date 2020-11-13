package ua.notky.cartridge.consumables.service.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.WorkingDay;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.workingday.WorkingDayService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ua.notky.cartridge.consumables.tools.data.AbstractModelTool.INVALID_ID;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_2;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.DEPARTMENT_4;
import static ua.notky.cartridge.consumables.tools.data.model.WorkingDayTool.*;

public class WorkingDayServiceTest extends AbstractTestService {
    @Autowired
    private WorkingDayService service;

    @Test
    @Transactional
    void create() {
        WorkingDay newWorkingDay = getNew();

        WorkingDay result = service.create(newWorkingDay);
        assertEquals(newWorkingDay, result);

        int newWorkingDayId = result.getId();
        assertEquals(service.get(newWorkingDayId), newWorkingDay);
    }

    @Test
    @Transactional
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(WORKING_DAY_2)));
    }

    @Test
    @Transactional
    void update() {
        WorkingDay updated = getUpdated(WORKING_DAY_2);

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
        assertEquals(service.get(ID_WORKING_DAY_2), WORKING_DAY_2);
        assertNotEquals(service.get(ID_WORKING_DAY_2), WORKING_DAY_3);
    }

    @Test
    void getWithDepartments(){
        WorkingDay workingDay = service.getWithDepartments(ID_WORKING_DAY_2);
        assertEquals(workingDay, WORKING_DAY_2);
        assertIterableEquals(workingDay.getDepartments(),
                List.of(DEPARTMENT_2, DEPARTMENT_4));
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_WORKING_DAY_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(WORKING_DAY_1, WORKING_DAY_2, WORKING_DAY_3, WORKING_DAY_4));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), WORKING_DAYS);
    }
}
