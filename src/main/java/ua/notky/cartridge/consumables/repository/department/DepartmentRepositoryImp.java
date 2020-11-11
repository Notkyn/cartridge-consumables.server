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
    public boolean delete(int id) {
        log.info("Delete from bd with id={}", id);
        return repository.delete(id) != 0;
    }

    @Override
    public List<Department> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudDepartmentRepository repository) {
        this.repository = repository;
    }
}
