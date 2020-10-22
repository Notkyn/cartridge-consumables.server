package ua.notky.cartridge.consumables.service.model.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.repository.department.DepartmentRepository;
import ua.notky.cartridge.consumables.service.model.DepartmentService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class DepartmentServiceImp implements DepartmentService {
    private DepartmentRepository repository;


    @Override
    public Department create(Department department) {
        log.info("Create new Department: {}", department);
        checkNotNull(department);
        checkNew(department);
        return repository.save(department);
    }

    @Override
    public Department update(Department department) {
        log.info("Update Department: {}", department);
        checkNotNull(department);
        checkUpdated(department);
        return repository.save(department);
    }

    @Override
    public Department get(int id) {
        log.info("Get one Department by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Department by id={}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Department> getAll() {
        log.info("Get all Departments");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(DepartmentRepository repository) {
        this.repository = repository;
    }
}
