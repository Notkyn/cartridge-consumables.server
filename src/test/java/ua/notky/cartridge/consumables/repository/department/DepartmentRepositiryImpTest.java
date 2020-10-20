package ua.notky.cartridge.consumables.repository.department;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.repository.AbstractTestRepository;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.DepartmentTool.*;

class DepartmentRepositiryImpTest extends AbstractTestRepository {
    @Autowired
    private DepartmentRepository repository;


    @Test
    @Transactional
    void save() {
        Department departmetnNew = getNew();

        Department result = repository.save(departmetnNew);
        assertEquals(departmetnNew, result);

        int departmentNewId = result.getId();
        assertEquals(repository.get(departmentNewId), departmetnNew);
    }

    @Test
    void get() {
        assertEquals(repository.get(ID_DEPARTMENT_2), DEPARTMENT_2);
        assertNotEquals(repository.get(ID_DEPARTMENT_2), DEPARTMENT_3);
        assertNull(repository.get(ID_NULL_DEPARTMENT));
    }

    @Test
    @Transactional
    void delete() {
        assertTrue(repository.delete(ID_DEPARTMENT_2));
        assertFalse(repository.delete(ID_NULL_DEPARTMENT));
        assertNull(repository.get(ID_DEPARTMENT_2));
    }

    @Test
    void getAll() {
        assertIterableEquals(repository.getAll(), DEPARTMENTS);
    }
}