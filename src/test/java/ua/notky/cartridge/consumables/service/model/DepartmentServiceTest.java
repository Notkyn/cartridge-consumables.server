package ua.notky.cartridge.consumables.service.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.department.DepartmentService;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.*;

class DepartmentServiceTest extends AbstractTestService {

    @Autowired
    private DepartmentService service;

    @Test
    @Transactional
    void create() {
        Department newDepartment = getNew();

        Department result = service.create(newDepartment);
        assertEquals(newDepartment, result);

        int newDepartmentId = result.getId();
        assertEquals(service.get(newDepartmentId), newDepartment);
    }

    @Test
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(DEPARTMENT_2)));
    }

    @Test
    void update() {
        Department updated = getUpdated(DEPARTMENT_2);

        assertEquals(service.update(updated), updated);
        assertEquals(service.get(updated.getId()), updated);
    }

    @Test
    @Transactional
    void updateIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void updateIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.update(getNew()));
    }

    @Test
    void get() {
        assertEquals(service.get(ID_DEPARTMENT_2), DEPARTMENT_2);
        assertNotEquals(service.get(ID_DEPARTMENT_2), DEPARTMENT_3);
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_DEPARTMENT_2);
        assertIterableEquals(service.getAll(),
                Arrays.asList(DEPARTMENT_1, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5));

    }

    @Test
    @Transactional
    void deleteNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.delete(INVALID_ID));
    }

    @Test
    void getAll() {
        assertIterableEquals(service.getAll(), DEPARTMENTS);
    }
}