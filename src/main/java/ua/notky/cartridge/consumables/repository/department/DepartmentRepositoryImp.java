package ua.notky.cartridge.consumables.repository.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.Department;

import java.util.List;

@Slf4j
@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {
    private CrudDepartmentRepository repository;

    @Override
    public Department save(Department department) {
        log.info("Save to bd: {}", department);
        return repository.save(department);
    }

    @Override
    public Department getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Department getWithCartridge(int id) {
        log.info("Find by Id [id={}] from bd with cartridge", id);
        return repository.getWithCartridge(id);
    }

    @Override
    public Department getWithWorkDays(int id) {
        log.info("Find by Id [id={}] from bd with work days", id);
        return repository.getWithWorkDays(id);
    }

    @Override
    public boolean delete(Department department) {
        log.info("Delete from bd with id={}", department.getId());
        repository.delete(department);
        return repository.findById(department.getId()).orElse(null) == null;
    }

    @Override
    public List<Department> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Override
    public List<Department> getAllWithCartridge() {
        log.info("Find all with cartriedge from bd");
        return repository.getAllWithCartridge();
    }

    @Autowired
    public void setRepository(CrudDepartmentRepository repository) {
        this.repository = repository;
    }
}
