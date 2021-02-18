package ua.notky.cartridge.consumables.service.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.service.AbstractTestService;
import ua.notky.cartridge.consumables.service.model.department.DepartmentService;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGES;
import static ua.notky.cartridge.consumables.tools.data.model.CartridgeTool.CARTRIDGE_2;
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
    @Transactional
    void createIllegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
    }

    @Test
    @Transactional
    void createIllegalEntity(){
        assertThrows(IllegalEntityException.class, () -> service.create(getUpdated(DEPARTMENT_2)));
    }

    @Test
    @Transactional
    void update() {
        Department updated = getUpdated(DEPARTMENT_2);

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
        assertEquals(service.get(ID_DEPARTMENT_2), DEPARTMENT_2);
        assertNotEquals(service.get(ID_DEPARTMENT_2), DEPARTMENT_3);
    }

    @Test
    void getWithCartridge(){
        Department department = service.getWithCartridge(ID_DEPARTMENT_2);
        assertEquals(department, DEPARTMENT_2);
        assertEquals(department.getCartridge(), CARTRIDGE_2);
    }

    @Test
    void getNotFound(){
        assertThrows(NotFoundDataException.class, () -> service.get(INVALID_ID));
    }

    @Test
    @Transactional
    void delete() {
        service.delete(ID_DEPARTMENT_5);
        assertIterableEquals(service.getAll(),
                Arrays.asList(DEPARTMENT_1, DEPARTMENT_2, DEPARTMENT_3, DEPARTMENT_4));

    }

    @Test
    @Transactional
    void deleteHasDependency() {
        assertThrows(HasDependencyException.class, () -> service.delete(ID_DEPARTMENT_2));
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

    @Test
    void getAllWithCartridge() {
        List<Department> departments = service.getAllWithCartridge();

        assertIterableEquals(departments, DEPARTMENTS);

        for(int i = 0; i < departments.size(); i++){
            int k = i;
            if(i == 4){
                k = 0;
            }
            assertEquals(departments.get(i).getCartridge(), CARTRIDGES.get(k));
        }
    }
}