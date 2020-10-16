package ua.notky.cartridge.consumables.service.model;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.Department;

import java.util.List;

@Transactional(readOnly = true)
public interface DepartmentService {

    @Transactional
    Department create(Department department);

    @Transactional
    Department update(Department department);

    Department get(int id);

    @Transactional
    void delete(int id);

    List<Department> getAll();
}
