package ua.notky.cartridge.consumables.repository.department;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.DepartmentTool.*;

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
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_DEPARTMENT_2));
        assertFalse(repository.delete(INVALID_ID));
        assertNull(repository.getById(ID_DEPARTMENT_2));
        assertIterableEquals(repository.getAll(),
                Arrays.asList(DEPARTMENT_1, DEPARTMENT_3, DEPARTMENT_4, DEPARTMENT_5));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), DEPARTMENTS);
    }
}