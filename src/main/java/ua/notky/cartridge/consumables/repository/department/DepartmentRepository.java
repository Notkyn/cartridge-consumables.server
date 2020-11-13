package ua.notky.cartridge.consumables.repository.department;

import ua.notky.cartridge.consumables.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department save(Department department);
    Department getById(int id);
    Department getWithCartridge(int id);
    Department getWithWorkDays(int id);
    boolean delete(Department department);
    List<Department> getAll();
}
