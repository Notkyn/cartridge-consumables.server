package ua.notky.cartridge.consumables.repository.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;
import ua.notky.cartridge.consumables.repository.department.DepartmentRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
import static ua.notky.cartridge.consumables.tools.data.model.DepartmentTool.*;
import static ua.notky.cartridge.consumables.tools.data.model.WorkingDayTool.WORKING_DAY_1;
import static ua.notky.cartridge.consumables.tools.data.model.WorkingDayTool.WORKING_DAY_2;

class DepartmentRepositiryTest extends AbstractTestRepository {
    @Autowired
    private DepartmentRepository repository;

    @Test
    @Transactional
    void save() {
        Department newDepartmetn = getNew();

        Department result = repository.save(newDepartmetn);
        assertEquals(newDepartmetn, result);

        int newDepartmentId = result.getId();
        assertEquals(repository.getById(newDepartmentId), newDepartmetn);
    }

    @Test
    void getById() {
        assertEquals(repository.getById(ID_DEPARTMENT_2), DEPARTMENT_2);
        assertNotEquals(repository.getById(ID_DEPARTMENT_2), DEPARTMENT_3);
        assertNull(repository.getById(INVALID_ID));
    }

    @Test
    void getWithCartridge(){
        Department department = repository.getWithCartridge(ID_DEPARTMENT_2);
        assertEquals(department, DEPARTMENT_2);
        assertEquals(department.getCartridge(), CARTRIDGE_2);
    }

    @Test
    void getWithWorkingDay(){
        Department department = repository.getWithWorkDays(ID_DEPARTMENT_2);
        assertEquals(department, DEPARTMENT_2);
        assertIterableEquals(department.getWorkingDays(),
                List.of(WORKING_DAY_1, WORKING_DAY_2));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(DEPARTMENT_5));
        assertNull(repository.getById(ID_DEPARTMENT_5));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), DEPARTMENTS);
    }
}