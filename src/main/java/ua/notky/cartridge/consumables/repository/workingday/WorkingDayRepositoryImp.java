package ua.notky.cartridge.consumables.repository.workingday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.model.WorkingDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class WorkingDayRepositoryImp implements WorkingDayRepository{
    private CrudWorkingDayRepository repository;

    @Override
    public WorkingDay save(WorkingDay workingDay) {
        log.info("Save to bd: {}", workingDay);
        return repository.save(workingDay);
    }

    @Override
    public WorkingDay getById(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public WorkingDay getWithDepartments(int id) {
        log.info("Find by Id [id={}] from bd", id);
        return repository.getWithDepartments(id);
    }

    @Override
    public boolean delete(WorkingDay workingDay) {
        log.info("Delete from bd with id={}", workingDay.getId());
        repository.delete(workingDay);
        return repository.findById(workingDay.getId()).orElse(null) == null;
    }

    @Override
    public List<WorkingDay> getAll() {
        log.info("Find all from bd");
        return repository.findAll();
    }

    @Autowired
    public void setRepository(CrudWorkingDayRepository repository) {
        this.repository = repository;
    }
}
