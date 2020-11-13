package ua.notky.cartridge.consumables.service.model.workingday;

import org.springframework.transaction.annotation.Transactional;
import ua.notky.cartridge.consumables.model.WorkingDay;

import java.util.List;

@Transactional(readOnly = true)
public interface WorkingDayService {
    @Transactional
    WorkingDay create(WorkingDay workingDay);

    @Transactional
    WorkingDay update(WorkingDay workingDay);

    WorkingDay get(int id);
    WorkingDay getWithDepartments(int id);

    @Transactional
    void delete(int id);

    List<WorkingDay> getAll();
}
