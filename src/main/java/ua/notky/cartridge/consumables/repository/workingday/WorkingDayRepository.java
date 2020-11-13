package ua.notky.cartridge.consumables.repository.workingday;

import ua.notky.cartridge.consumables.model.WorkingDay;

import java.util.List;

public interface WorkingDayRepository {
    WorkingDay save(WorkingDay workingDay);
    WorkingDay getById(int id);
    WorkingDay getWithDepartments(int id);
    boolean delete(WorkingDay workingDay);
    List<WorkingDay> getAll();
}
