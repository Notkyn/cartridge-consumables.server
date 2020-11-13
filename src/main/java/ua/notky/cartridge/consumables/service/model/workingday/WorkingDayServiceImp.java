package ua.notky.cartridge.consumables.service.model.workingday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.notky.cartridge.consumables.model.WorkingDay;
import ua.notky.cartridge.consumables.repository.workingday.WorkingDayRepository;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.*;

@Slf4j
@Service
public class WorkingDayServiceImp implements WorkingDayService {
    private WorkingDayRepository repository;

    @Override
    public WorkingDay create(WorkingDay workingDay) {
        log.info("Create new Working Day: {}", workingDay);
        checkNotNull(workingDay);
        checkNew(workingDay);
        return repository.save(workingDay);
    }

    @Override
    public WorkingDay update(WorkingDay workingDay) {
        log.info("Update Working Day: {}", workingDay);
        checkNotNull(workingDay);
        checkUpdated(workingDay);
        return repository.save(workingDay);
    }

    @Override
    public WorkingDay get(int id) {
        log.info("Get one Working Day by id={}", id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Override
    public WorkingDay getWithDepartments(int id) {
        log.info("Get one Working Day by id={} with Departments", id);
        return checkNotFoundWithId(repository.getWithDepartments(id), id);
    }

    @Override
    public void delete(int id) {
        log.info("Delete Working Day by id={}", id);
        WorkingDay workingDay = repository.getById(id);
        checkNotFoundWithId(workingDay, id);
        repository.delete(workingDay);
    }

    @Override
    public List<WorkingDay> getAll() {
        log.info("Get all Working Days");
        return repository.getAll();
    }

    @Autowired
    public void setRepository(WorkingDayRepository repository) {
        this.repository = repository;
    }
}
