package ua.notky.cartridge.consumables.repository.workingday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.WorkingDay;

public interface CrudWorkingDayRepository extends JpaRepository<WorkingDay, Integer> {

    @Query("SELECT w FROM WorkingDay w JOIN FETCH w.departments WHERE w.id=?1")
    WorkingDay getWithDepartments(int id);
}
